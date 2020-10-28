package me.amryousef.comics

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import me.amryousef.comics.presentation.ComicsListState
import me.amryousef.comics.presentation.ComicsListViewModel
import me.amryousef.lib.presentation.ViewState
import me.amryousef.marvelcomics.comics.R
import me.amryousef.marvelcomics.comics.databinding.FragmentComicsBinding
import javax.inject.Inject

class ComicsListFragment : Fragment(R.layout.fragment_comics) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mapper: ViewDataMapper

    private val viewModel: ComicsListViewModel by viewModels { viewModelFactory }
    private val adapter = ComicsAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentComicsBinding.bind(view)
        binding.comicsList.adapter = adapter
        binding.comicsListErrorRetryButton.setOnClickListener { viewModel.loadData() }
        viewModel.state.observe(viewLifecycleOwner) { binding.renderState(it) }
    }

    private fun FragmentComicsBinding.renderState(state: ViewState<ComicsListState>) = when (state) {
        is ViewState.Ready -> renderReady(state)
        is ViewState.Error -> renderError()
        is ViewState.Loading -> renderLoading()
    }

    private fun FragmentComicsBinding.renderError() {
        comicsListErrorGroup.isVisible = true
        comicsListProgress.isVisible = false
        comicsList.isVisible = false
    }

    private fun FragmentComicsBinding.renderReady(state: ViewState.Ready<ComicsListState>) {
        adapter.submitList(mapper.from(state.data.items))
        comicsList.isVisible = true
        comicsListProgress.isVisible = false
        comicsListErrorGroup.isVisible = false
    }

    private fun FragmentComicsBinding.renderLoading() {
        comicsListProgress.isVisible = true
        comicsList.isVisible = false
        comicsListErrorGroup.isVisible = false
    }
}
