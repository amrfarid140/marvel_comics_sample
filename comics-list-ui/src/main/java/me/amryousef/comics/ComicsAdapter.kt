package me.amryousef.comics

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class ComicsAdapter : ListAdapter<ComicItemData, ComicItemViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComicItemViewHolder(parent)

    override fun onBindViewHolder(holder: ComicItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    private class ItemCallback : DiffUtil.ItemCallback<ComicItemData>() {
        override fun areItemsTheSame(oldItem: ComicItemData, newItem: ComicItemData) = true

        override fun areContentsTheSame(oldItem: ComicItemData, newItem: ComicItemData) =
            oldItem == newItem
    }
}
