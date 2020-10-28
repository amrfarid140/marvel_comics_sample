package me.amryousef.comics.data.api

import com.squareup.moshi.Json

data class ComicThumbnailData(
    @Json(name = "path")
    val path: String?,
    @Json(name = "extension")
    val extension: String?
)
