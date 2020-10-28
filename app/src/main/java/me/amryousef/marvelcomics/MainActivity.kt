package me.amryousef.marvelcomics

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.MaterialToolbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import me.amryousef.marvelcomics.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportFragmentManager
            .fragments
            .filterIsInstance<NavHostFragment>()
            .firstOrNull()
            ?.navController
            ?.addOnDestinationChangedListener(
                TitleChangeListener(binding.mainToolbar)
            )
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    private class TitleChangeListener(
        private val toolbar: MaterialToolbar
    ) : NavController.OnDestinationChangedListener {
        override fun onDestinationChanged(
            controller: NavController,
            destination: NavDestination,
            arguments: Bundle?
        ) {
            when (destination.id) {
                R.id.comicsListFragment -> {
                    toolbar.title = toolbar.context.getString(R.string.toolbar_title_comics_list)
                }
                R.id.comicDetailFragment ->
                    toolbar.title = toolbar.context.getString(R.string.toolbar_title_comic_details)
            }
        }
    }
}
