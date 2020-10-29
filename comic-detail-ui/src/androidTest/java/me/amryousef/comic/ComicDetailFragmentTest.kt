package me.amryousef.comic

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import me.amryousef.comic.presentation.ComicDetailState
import me.amryousef.comic.presentation.ComicDetailViewModel
import me.amryousef.lib.presentation.ViewState
import me.amryousef.lib.ui.Navigator
import me.amryousef.lib.ui.test.fragmentTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComicDetailFragmentTest {

    @get:Rule
    val instantTaskExecutable = InstantTaskExecutorRule()

    private val mockViewModel = mockk<ComicDetailViewModel>(relaxed = true) {
        every { loadData(any()) } returns ViewState.Error
    }
    private val mockNavigator = mockk<Navigator> {
        every { pop() } just Runs
    }

    @Test
    fun givenComicId_WhenViewStateIsError_ThenPopNavigator() =
        fragmentTest(bundle = Bundle().apply { putLong("id", 123) }, injector = ::inject) {
            it.onActivity { verify { mockNavigator.pop() } }
        }

    @Test
    fun givenComicId_WhenViewStateIsReady_ThenDetailsIsVisible() {
        every { mockViewModel.loadData(any()) } returns ViewState.Ready(
            ComicDetailState(
                "https://test.com",
                ".png",
                "Test Title",
                "Test Description"
            )
        )
        fragmentTest(bundle = Bundle().apply { putLong("id", 123) }, injector = ::inject) {
            onView(withText("Test Title")).check(matches(isDisplayed()))
            onView(withText("Test Description")).check(matches(isDisplayed()))
        }
    }

    private fun inject(fragment: ComicDetailFragment) {
        fragment.viewModelFactory = mockk {
            every { create<ComicDetailViewModel>(any()) } returns (mockViewModel)
        }
        fragment.navigator = mockNavigator
    }
}
