package me.amryousef.comics.data

import kotlinx.coroutines.withContext
import me.amryousef.comics.data.api.ComicsService
import me.amryousef.comics.domain.ComicsPage
import me.amryousef.comics.domain.ComicsRepository
import me.amryousef.lib.domain.CoroutineContextProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachedComicsRepository @Inject constructor(
    private val contextProvider: CoroutineContextProvider,
    private val comicsService: ComicsService,
    private val comicsPageMapper: ComicsPageMapper
) : ComicsRepository {

    private val inMemoryPages = mutableMapOf<Int, ComicsPage>()

    private companion object {
        const val DEFAULT_LIMIT = 20
    }

    override suspend fun fetchComicsPage(pageNumber: Int) = withContext(contextProvider.io()) {
        inMemoryPages[pageNumber] ?: run {
            val apiResponse = comicsService.getComics((pageNumber - 1) * DEFAULT_LIMIT)
            val page = comicsPageMapper.map(apiResponse, pageNumber)
            inMemoryPages[pageNumber] = page
            page
        }
    }
}
