package me.amryousef.comics.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import me.amryousef.comics.data.api.ComicDataWrapper
import me.amryousef.comics.data.api.ComicsService
import me.amryousef.comics.domain.ComicsPage
import me.amryousef.lib.domain.CoroutineContextProvider
import org.junit.Test

class CachedComicsRepositoryTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val mockCoroutineContextProvider = mockk<CoroutineContextProvider> {
        every { io() } returns testDispatcher
    }
    private val mockComicsService = mockk<ComicsService>(relaxed = true)
    private val mockMapper = mockk<ComicsPageMapper>(relaxed = true)

    private val subject by lazy {
        CachedComicsRepository(
            contextProvider = mockCoroutineContextProvider,
            comicsPageMapper = mockMapper,
            comicsService = mockComicsService
        )
    }

    @Test
    fun givenNewPageNumber_WhenFetchComicsPageIsCalled_ThenServiceIsUsedToFetchData() = testDispatcher.runBlockingTest {
        coEvery { mockComicsService.getComics(any()) } returns ComicDataWrapper(null)
        every { mockMapper.map(any(), any()) } returns ComicsPage(1, emptyList())
        subject.fetchComicsPage(1)
        coVerify { mockComicsService.getComics(any()) }
        verify { mockMapper.map(any(), any()) }
    }

    @Test
    fun givenCachedPageNumber_WhenFetchComicsPageIsCalled_ThenServiceIsNotUsedToFetchData() = testDispatcher.runBlockingTest {
        coEvery { mockComicsService.getComics(any()) } returns ComicDataWrapper(null)
        every { mockMapper.map(any(), any()) } returns ComicsPage(1, emptyList())
        subject.fetchComicsPage(1)
        subject.fetchComicsPage(1)
        coVerify(exactly = 1) { mockComicsService.getComics(any()) }
        verify(exactly = 1) { mockMapper.map(any(), any()) }
    }
}
