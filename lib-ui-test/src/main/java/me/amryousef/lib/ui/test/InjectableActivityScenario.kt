package me.amryousef.lib.ui.test

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.test.core.app.ActivityScenario
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import dagger.android.HasAndroidInjector
import me.amryousef.marvelcomics.lib.ui.testing.R
import javax.inject.Provider

class TestActivity : FragmentActivity(), HasAndroidInjector {
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            FragmentContainerView(this).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                id = R.id.test_fragment_container
            }
        )
    }
}

class InjectableActivityScenario {

    val scenario = ActivityScenario.launch(TestActivity::class.java)

    inline fun <reified F : Fragment> injectFragment(
        crossinline injector: F.() -> Unit
    ) {
        val provider: Provider<AndroidInjector.Factory<*>> = Provider {
            AndroidInjector.Factory<F> {
                AndroidInjector<F> { instance ->
                    instance.injector()
                }
            }
        }
        scenario.onActivity {
            it.dispatchingAndroidInjector = DispatchingAndroidInjector_Factory<Any>(
                { mutableMapOf(F::class.java to provider) },
                { mutableMapOf(F::class.java.canonicalName to provider) }
            ).get()
        }
    }

    fun displayFragment(fragment: Fragment) {
        scenario.onActivity {
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.test_fragment_container, fragment)
                .commitAllowingStateLoss()
        }
    }
}

inline fun <reified F : Fragment> fragmentTest(
    bundle: Bundle? = null,
    crossinline injector: (fragment: F) -> Unit,
    crossinline test: (scenario: ActivityScenario<TestActivity>) -> Unit
) {
    val activityScenario = InjectableActivityScenario()
    activityScenario.scenario.onActivity {
        activityScenario.injectFragment<F> {
            injector(this)
        }
    }
    activityScenario.scenario.onActivity {
        val fragment = F::class.java.newInstance().apply {
            arguments = bundle
        }
        activityScenario.displayFragment(fragment)
    }
    test(activityScenario.scenario)
    activityScenario.scenario.close()
}
