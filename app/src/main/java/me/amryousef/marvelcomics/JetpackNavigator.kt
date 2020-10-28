package me.amryousef.marvelcomics

import androidx.navigation.fragment.NavHostFragment
import dagger.Reusable
import me.amryousef.comics.ComicsListFragmentDirections
import me.amryousef.lib.ui.Navigator
import javax.inject.Inject

@Reusable
class JetpackNavigator @Inject constructor(val activity: MainActivity) : Navigator {
    private val navController
        get() = activity
            .supportFragmentManager
            .fragments
            .filterIsInstance<NavHostFragment>()
            .firstOrNull()
            ?.navController

    override fun navigateToComicDetail(comicId: Long) {
        navController?.navigate(
            ComicsListFragmentDirections.actionComicsListFragmentToComicDetailFragment(comicId)
        )
    }

    override fun pop() {
        navController?.popBackStack()
    }
}
