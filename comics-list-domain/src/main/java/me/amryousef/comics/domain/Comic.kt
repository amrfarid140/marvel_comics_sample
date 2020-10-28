package me.amryousef.comics.domain

data class Comic(
    val id: Long,
    val title: String,
    val issueNumber: Double,
    val description: String,
    val imageUrl: String,
    val imageExtension: String,
    val creators: List<ComicCreator>
)
