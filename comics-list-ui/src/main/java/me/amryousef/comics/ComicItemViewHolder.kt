package me.amryousef.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.amryousef.marvelcomics.comics.databinding.RowComicItemBinding

class ComicItemViewHolder(
    parent: ViewGroup,
    private val binding: RowComicItemBinding = RowComicItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ComicItemData) {
        binding.comicItemTitle.text = item.title
        binding.comicItemImage.load(item.imageUrl)
    }
}
