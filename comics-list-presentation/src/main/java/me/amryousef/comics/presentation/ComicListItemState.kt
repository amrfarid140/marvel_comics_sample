package me.amryousef.comics.presentation

sealed class ComicListItemState {
    object LoadingPlaceholder : ComicListItemState()
    data class Item(
        val title: String,
        val imageUrl: String,
        val imageExtension: String
    ) : ComicListItemState()
}
