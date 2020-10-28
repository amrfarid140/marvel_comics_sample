package me.amryousef.comic

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import me.amryousef.marvelcomics.comic.R

class ComicDetailFragment : Fragment(R.layout.fragment_comic_detail) {
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
