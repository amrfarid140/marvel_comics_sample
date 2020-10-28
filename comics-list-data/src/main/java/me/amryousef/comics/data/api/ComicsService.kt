package me.amryousef.comics.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsService {
    @GET("comics?format=comic&formatType=comic&limit=20")
    suspend fun getComics(@Query("offset") offset: Int): ComicDataWrapper
}
