package me.amryousef.marvelcomics.di

import dagger.Binds
import dagger.Module
import me.amryousef.lib.domain.CoroutineContextProvider
import me.amryousef.marvelcomics.AndroidCoroutineContextProvider

@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContextProvider(
        androidCoroutineContextProvider: AndroidCoroutineContextProvider
    ): CoroutineContextProvider
}
