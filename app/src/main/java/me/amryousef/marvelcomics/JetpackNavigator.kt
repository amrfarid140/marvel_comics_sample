package me.amryousef.marvelcomics

import androidx.navigation.fragment.NavHostFragment
import dagger.Reusable
import me.amryousef.comics.ComicsListFragmentDirections
import me.amryousef.lib.ui.Navigator
import javax.inject.Inject

@Reusable
class JetpackNavigator @Inject constructor(val activity: MainActivity) : Navigator {
    override fun navigateToComicDetail(comicId: Long) {
        activity.supportFragmentManager.fragments.filterIsInstance<NavHostFragment>()
            .firstOrNull()?.navController?.navigate(
            ComicsListFragmentDirections.actionComicsListFragmentToComicDetailFragment(comicId)
        )
    }
}
