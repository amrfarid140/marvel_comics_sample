package me.amryousef.comics.data.api

import com.squareup.moshi.Json

data class ComicCreatorSummary(
    @Json(name = "name")
    val name: String?,
    @Json(name = "role")
    val role: String?
)
