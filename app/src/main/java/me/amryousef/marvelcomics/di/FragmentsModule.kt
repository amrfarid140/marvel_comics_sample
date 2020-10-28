package me.amryousef.marvelcomics.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.amryousef.comics.ComicsListFragment

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeComicsListFragment(): ComicsListFragment
}
