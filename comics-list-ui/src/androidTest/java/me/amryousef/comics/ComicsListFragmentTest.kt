package me.amryousef.comics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import me.amryousef.comics.presentation.ComicsListEvent
import me.amryousef.comics.presentation.ComicsListNavigation
import me.amryousef.comics.presentation.ComicsListState
import me.amryousef.comics.presentation.ComicsListViewModel
import me.amryousef.lib.presentation.ViewState
import me.amryousef.lib.ui.Navigator
import me.amryousef.lib.ui.test.fragmentTest
import me.amryousef.marvelcomics.comics.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComicsListFragmentTest {
    @get:Rule
    val instantTaskExecutable = InstantTaskExecutorRule()

    private val testState = MutableLiveData<ViewState<ComicsListState>>()
    private val testNavigation = MutableLiveData<ComicsListNavigation>()
    private val testEvent = MutableLiveData<ComicsListEvent>()
    private val mockViewModel = mockk<ComicsListViewModel>(relaxUnitFun = true) {
        every { state } returns testState
        every { navigation } returns testNavigation
        every { event } returns testEvent
    }

    private val mockNavigator = mockk<Navigator>(relaxed = true)
    private val mockMapper = spyk<ViewDataMapper>()

    private fun inject(fragment: ComicsListFragment) {
        fragment.navigator = mockNavigator
        fragment.mapper = mockMapper
        fragment.viewModelFactory = mockk {
            every { create(ComicsListViewModel::class.java) } returns mockViewModel
        }
    }

    @Test
    fun givenStateIsError_WhenStateIsObserved_ThenErrorStateIsVisible() =
        fragmentTest(injector = ::inject) {
            testState.value = ViewState.Error
            onView(withId(R.id.comics_list_error_group)).check(matches(isDisplayed()))
            onView(withId(R.id.comics_list_progress)).check(
                matches(
                    withEffectiveVisibility(
                        ViewMatchers.Visibility.GONE
                    )
                )
            )
            onView(withId(R.id.comics_list)).check(
                matches(
                    withEffectiveVisibility(
                        ViewMatchers.Visibility.GONE
                    )
                )
            )
        }

    @Test
    fun givenStateIsLoading_WhenStateIsObserved_ThenLoadingStateIsVisible() =
        fragmentTest(injector = ::inject) {
            testState.value = ViewState.Loading
            onView(withId(R.id.comics_list_progress)).check(matches(isDisplayed()))
            onView(withId(R.id.comics_list)).check(
                matches(
                    withEffectiveVisibility(
                        ViewMatchers.Visibility.GONE
                    )
                )
            )
            onView(withId(R.id.comics_list_error_group)).check(
                matches(
                    withEffectiveVisibility(
                        ViewMatchers.Visibility.GONE
                    )
                )
            )
        }

    @Test
    fun givenStateIsReady_WhenStateIsObserved_ThenReadyStateIsVisible() =
        fragmentTest(injector = ::inject) {
            testState.value = ViewState.Ready(
                ComicsListState(1, emptyList())
            )
            onView(withId(R.id.comics_list)).check(matches(isDisplayed()))
            onView(withId(R.id.comics_list_progress)).check(
                matches(
                    withEffectiveVisibility(
                        ViewMatchers.Visibility.GONE
                    )
                )
            )
            onView(withId(R.id.comics_list_error_group)).check(
                matches(
                    withEffectiveVisibility(
                        ViewMatchers.Visibility.GONE
                    )
                )
            )
        }
}
