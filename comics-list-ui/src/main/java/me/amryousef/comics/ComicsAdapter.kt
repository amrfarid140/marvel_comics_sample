package me.amryousef.comics

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class ComicsAdapter(private val onItemSelected: (position: Int) -> Unit) :
    ListAdapter<ComicItemData, ComicListViewHolder<*>>(ItemCallback()) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ComicItemData.Item -> ComicItemData.ViewType.ITEM.ordinal
        is ComicItemData.LoadingPlaceholder -> ComicItemData.ViewType.LOADING.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (ComicItemData.ViewType.values()[viewType]) {
            ComicItemData.ViewType.LOADING -> ComicListLoadingPlaceholder(parent)
            ComicItemData.ViewType.ITEM -> ComicItemViewHolder(parent) {
                onItemSelected(it)
            }
        }

    override fun onBindViewHolder(holder: ComicListViewHolder<*>, position: Int) =
        when (val data = getItem(position)) {
            is ComicItemData.Item -> (holder as ComicItemViewHolder).bind(data, position)
            is ComicItemData.LoadingPlaceholder -> (holder as ComicListLoadingPlaceholder).bind(
                data,
                position
            )
        }

    private class ItemCallback : DiffUtil.ItemCallback<ComicItemData>() {
        override fun areItemsTheSame(oldItem: ComicItemData, newItem: ComicItemData) =
            oldItem::class.java == newItem::class.java

        override fun areContentsTheSame(oldItem: ComicItemData, newItem: ComicItemData) =
            oldItem == newItem
    }
}
