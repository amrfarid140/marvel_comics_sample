package me.amryousef.marvelcomics.di

import dagger.Binds
import dagger.Module
import me.amryousef.comics.data.CachedComicsRepository
import me.amryousef.comics.domain.ComicsRepository

@Module
abstract class DataModuleBinding {
    @Binds
    abstract fun bindComicRepository(cachedComicsRepository: CachedComicsRepository): ComicsRepository
}
