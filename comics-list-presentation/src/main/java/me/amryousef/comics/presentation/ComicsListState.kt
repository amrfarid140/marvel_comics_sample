package me.amryousef.comics.presentation

data class ComicsListState(
    val currentPage: Int,
    val items: List<ComicListItemState>
)
