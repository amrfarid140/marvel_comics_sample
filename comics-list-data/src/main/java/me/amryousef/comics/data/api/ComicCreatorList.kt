package me.amryousef.comics.data.api

import com.squareup.moshi.Json

data class ComicCreatorList(
    @Json(name = "items")
    val items: List<ComicCreatorSummary>?
)
