package me.amryousef.comic.presentation

import androidx.lifecycle.ViewModel
import me.amryousef.comics.domain.ComicsRepository
import me.amryousef.lib.presentation.ViewState
import javax.inject.Inject

class ComicDetailViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository
) : ViewModel() {

    fun loadData(comicId: Long): ViewState<ComicDetailState> {
        val comic = comicsRepository.getComic(comicId)
        return if (comic != null) {
            ViewState.Ready(
                ComicDetailState(
                    imageUrl = comic.imageUrl,
                    imageExtension = comic.imageExtension,
                    title = comic.title,
                    description = comic.description.takeIf { it.isNotEmpty() }
                )
            )
        } else {
            ViewState.Error
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
