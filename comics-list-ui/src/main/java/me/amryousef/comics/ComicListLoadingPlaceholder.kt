package me.amryousef.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import me.amryousef.marvelcomics.comics.R

class ComicListLoadingPlaceholder(
    parent: ViewGroup
) : ComicListViewHolder<ComicItemData.LoadingPlaceholder>(
    LayoutInflater.from(parent.context).inflate(R.layout.row_loading_placeholder, parent, false)
) {
    override fun bind(data: ComicItemData.LoadingPlaceholder, position: Int) {
        // NO-OP
    }
}
