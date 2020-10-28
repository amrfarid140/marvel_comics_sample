package me.amryousef.comic

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import me.amryousef.comic.presentation.ComicDetailViewModel
import me.amryousef.lib.presentation.ViewState
import me.amryousef.lib.ui.Navigator
import me.amryousef.marvelcomics.comic.R
import me.amryousef.marvelcomics.comic.databinding.FragmentComicDetailBinding
import javax.inject.Inject

class ComicDetailFragment : Fragment(R.layout.fragment_comic_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    private val viewModel: ComicDetailViewModel by viewModels { viewModelFactory }

    val args: ComicDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val state = viewModel.loadData(args.id)
        if (state is ViewState.Ready) {
            val binding = FragmentComicDetailBinding.bind(view)
            Picasso
                .get()
                .load("${state.data.imageUrl}/landscape_incredible.${state.data.imageExtension}")
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(binding.comicDetailThumbnail)
            binding.comicDetailTitle.text = state.data.title
            binding.comicDetailDescription.text = state.data.description ?: getString(
                R.string.comic_detail_description_error_text
            )
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.comic_detail_error_text),
                Toast.LENGTH_SHORT
            ).show()
            navigator.pop()
        }
    }
}
