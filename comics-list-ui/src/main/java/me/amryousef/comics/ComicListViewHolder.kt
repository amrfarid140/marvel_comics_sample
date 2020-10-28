package me.amryousef.comics

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ComicListViewHolder<DATA : ComicItemData>(view: View) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(data: DATA, position: Int)
}
