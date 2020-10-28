package me.amryousef.comics

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.amryousef.marvelcomics.comics.R
import me.amryousef.marvelcomics.comics.databinding.RowComicItemBinding

class ComicItemViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.row_comic_item, parent, false)
) {
    fun bind(item: ComicItemData) {
        val binding = RowComicItemBinding.bind(itemView)
        binding.comicItemTitle.text = item.title
        Log.d("AMR", "${item.imageUrl}/landscape_incredible.${item.imageExtension}")
        Picasso
            .get()
            .load("${item.imageUrl}/landscape_incredible.${item.imageExtension}")
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(binding.comicItemImage)
    }
}
