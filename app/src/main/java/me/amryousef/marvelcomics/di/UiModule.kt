package me.amryousef.marvelcomics.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.amryousef.marvelcomics.MainActivity

@Module
abstract class UiModule {
    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
