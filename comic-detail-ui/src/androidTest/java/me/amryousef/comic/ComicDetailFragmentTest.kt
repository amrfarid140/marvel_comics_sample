package me.amryousef.comic

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import me.amryousef.comic.presentation.ComicDetailViewModel
import me.amryousef.lib.presentation.ViewState
import me.amryousef.lib.ui.Navigator
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComicDetailFragmentTest {

    @get:Rule
    val instantTaskExecutable = InstantTaskExecutorRule()

    @get:Rule
    val rule = ActivityTestRule(TestActivity::class.java)

    private val mockViewModel = mockk<ComicDetailViewModel>(relaxed = true) {
        every { loadData(any()) } returns ViewState.Error
    }
    private val mockNavigator = mockk<Navigator> {
        every { pop() } just Runs
    }

    @Test
    fun test() {
        val x = InjectableActivityScenario()
        x.injectFragment<ComicDetailFragment> {
            viewModelFactory = mockk {
                every { create<ComicDetailViewModel>(any()) } returns(mockViewModel)
            }
            navigator = mockNavigator
        }
        x.displayFragment(
            ComicDetailFragment().apply {
                arguments = Bundle().apply { putLong("id", 123) }
            }
        )
        x.scenario.onActivity {
            verify { mockNavigator.pop() }
        }
    }
}
