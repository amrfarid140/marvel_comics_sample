package me.amryousef.comics.data.api

import com.squareup.moshi.Json

data class ComicData(
    @Json(name = "id")
    val id: Long?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "issueNumber")
    val issueNumber: Double?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "thumbnail")
    val thumbnail: ComicThumbnailData?,
    @Json(name = "creators")
    val creators: ComicCreatorList?
)
