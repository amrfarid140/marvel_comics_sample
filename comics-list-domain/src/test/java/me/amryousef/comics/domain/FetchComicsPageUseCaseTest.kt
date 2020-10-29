package me.amryousef.comics.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import me.amryousef.lib.domain.UseCaseResult
import org.junit.Test
import kotlin.test.assertTrue

class FetchComicsPageUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val mockComicsRepository = mockk<ComicsRepository>()

    private val subject by lazy {
        FetchComicsPageUseCase(mockComicsRepository)
    }

    @Test
    fun givenRepositoryFails_WhenExecute_ThenUseCaseIsError() = testDispatcher.runBlockingTest {
        coEvery { mockComicsRepository.fetchComicsPage(any()) } throws IllegalStateException()
        val result = subject.execute(1)
        assertTrue(result is UseCaseResult.Error)
    }

    @Test
    fun givenRepositorySuccess_WhenExecute_ThenUseCaseIsSuccess() = testDispatcher.runBlockingTest {
        coEvery { mockComicsRepository.fetchComicsPage(any()) } returns ComicsPage(
            1,
            emptyList()
        )
        val result = subject.execute(1)
        assertTrue(result is UseCaseResult.Success)
    }
}
