package me.amryousef.marvelcomics.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.amryousef.comic.ComicDetailFragment
import me.amryousef.comics.ComicsListFragment
import me.amryousef.lib.ui.Navigator
import me.amryousef.marvelcomics.JetpackNavigator

@Module
abstract class FragmentsModule {

    @Binds
    abstract fun bindNavigator(jetpackNavigator: JetpackNavigator): Navigator

    @ContributesAndroidInjector
    abstract fun contributeComicsListFragment(): ComicsListFragment

    @ContributesAndroidInjector
    abstract fun contributeComicDetailFragment(): ComicDetailFragment
}
