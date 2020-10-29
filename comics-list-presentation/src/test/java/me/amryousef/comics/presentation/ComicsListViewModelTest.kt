package me.amryousef.comics.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import me.amryousef.comics.domain.ComicsPage
import me.amryousef.comics.domain.FetchComicsPageUseCase
import me.amryousef.lib.domain.UseCaseResult
import me.amryousef.lib.presentation.ViewState
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ComicsListViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    private val mockFetchComicsPageUseCase = mockk<FetchComicsPageUseCase> {
        coEvery { execute(any()) } returns UseCaseResult.Success(
            ComicsPage(1, emptyList())
        )
    }
    private val mockStateMapper = mockk<ComicsListStateMapper> {
        every { map(any()) } returns
            ComicsListState(
                1,
                listOf(
                    ComicListItemState.Item(
                        id = 1L,
                        title = "test",
                        imageExtension = ".png",
                        imageUrl = "https://test.com"
                    )
                )
            )
    }

    private val subject by lazy {
        ComicsListViewModel(
            mockFetchComicsPageUseCase,
            mockStateMapper
        )
    }

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun givenUseCaseSuccess_WhenViewModelIsBuilt_ThenStateIsReady() {
        assertTrue(subject.state.value is ViewState.Ready)
    }

    @Test
    fun givenUseCaseFails_WhenViewModelIsBuilt_ThenStateIsReady() {
        coEvery { mockFetchComicsPageUseCase.execute(any()) } returns
            UseCaseResult.Error()
        assertTrue(subject.state.value is ViewState.Error)
    }

    @Test
    fun givenUseCaseRunning_WhenLoadDataIsCalled_ThenLoadingPlaceholderExists() {
        subject.state
        coEvery {
            mockFetchComicsPageUseCase.execute(any())
        } answers {
            val state = subject.state.value
            assertTrue(state is ViewState.Ready)
            assertEquals(
                1,
                state.data.items.filterIsInstance<ComicListItemState.LoadingPlaceholder>().size,
            )
            UseCaseResult.Error()
        }
        subject.loadData()
    }

    @Test
    fun givenUseCaseError_WhenLoadDataIsCalled_ThenLoadingPlaceholderIsRemoved() {
        subject.state
        coEvery { mockFetchComicsPageUseCase.execute(any()) } returns UseCaseResult.Error()
        subject.loadData()
        val state = subject.state.value
        assertTrue(state is ViewState.Ready)
        assertTrue(
            state.data.items.filterIsInstance<ComicListItemState.LoadingPlaceholder>().isEmpty()
        )
    }

    @Test
    fun givenUseCaseSuccess_WhenLoadDataIsCalled_ThenLoadingPlaceholderIsRemoved() {
        subject.state
        subject.loadData()
        val state = subject.state.value
        assertTrue(state is ViewState.Ready)
        assertTrue(
            state.data.items.filterIsInstance<ComicListItemState.LoadingPlaceholder>().isEmpty()
        )
    }

    @Test
    fun givenUseCaseSuccess_WhenLoadDataIsCalled_ThenOldAndPreviousComicsAreCombined() {
        subject.state
        subject.loadData()
        val state = subject.state.value
        assertTrue(state is ViewState.Ready)
        assertEquals(
            2,
            state.data.items.size
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
