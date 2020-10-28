package me.amryousef.comics

sealed class ComicItemData {
    data class Item(
        val title: String,
        val imageUrl: String,
        val imageExtension: String
    ) : ComicItemData()

    object LoadingPlaceholder : ComicItemData()

    enum class ViewType {
        LOADING, ITEM
    }
}
