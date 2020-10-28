package me.amryousef.comics

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import me.amryousef.marvelcomics.comics.R
import me.amryousef.marvelcomics.comics.databinding.RowComicItemBinding

class ComicItemViewHolder(
    parent: ViewGroup,
    private val onItemSelected: (position: Int) -> Unit
) : ComicListViewHolder<ComicItemData.Item>(
    LayoutInflater.from(parent.context).inflate(R.layout.row_comic_item, parent, false)
) {

    override fun bind(data: ComicItemData.Item, position: Int) {
        val binding = RowComicItemBinding.bind(itemView)
        binding.root.setOnClickListener { onItemSelected(position) }
        binding.comicItemTitle.text = data.title
        Log.d("AMR", "${data.imageUrl}/landscape_incredible.${data.imageExtension}")
        Picasso
            .get()
            .load("${data.imageUrl}/landscape_incredible.${data.imageExtension}")
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(binding.comicItemImage)
    }
}
