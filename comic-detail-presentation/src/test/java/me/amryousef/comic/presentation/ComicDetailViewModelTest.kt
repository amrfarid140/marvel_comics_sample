package me.amryousef.comic.presentation

import io.mockk.coEvery
import io.mockk.mockk
import me.amryousef.comics.domain.Comic
import me.amryousef.comics.domain.ComicsRepository
import me.amryousef.lib.presentation.ViewState
import org.junit.Test
import kotlin.test.assertTrue

class ComicDetailViewModelTest {

    private val mockComicsRepository = mockk<ComicsRepository>()
    private val subject by lazy {
        ComicDetailViewModel(mockComicsRepository)
    }

    @Test
    fun givenRepositoryHasNoComic_WhenLoadData_ThenStateIsError() {
        coEvery { mockComicsRepository.getComic(any()) } returns null
        val result = subject.loadData(1)
        assertTrue(result is ViewState.Error)
    }

    @Test
    fun givenRepositoryHasComic_WhenLoadData_ThenStateIsReady() {
        val mockComic = mockk<Comic>(relaxed = true)
        coEvery { mockComicsRepository.getComic(any()) } returns mockComic
        val result = subject.loadData(1)
        assertTrue(result is ViewState.Ready)
    }
}
