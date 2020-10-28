package me.amryousef.comics.domain

interface ComicsRepository {
    suspend fun fetchComicsPage(pageNumber: Int): ComicsPage
}
