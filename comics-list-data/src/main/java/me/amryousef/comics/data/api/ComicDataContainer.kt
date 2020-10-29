package me.amryousef.comics.data.api

import com.squareup.moshi.Json

data class ComicDataContainer(
    @Json(name = "results")
    val results: List<ComicData>?
)
