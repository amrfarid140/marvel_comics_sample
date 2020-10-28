package me.amryousef.comics.data.api

import com.squareup.moshi.Json

data class ComicDataWrapper(
    @Json(name = "data")
    val data: ComicDataContainer?
)
