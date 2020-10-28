package me.amryousef.comics.presentation

import dagger.Reusable
import me.amryousef.comics.domain.ComicsPage
import javax.inject.Inject

@Reusable
class ComicsListStateMapper @Inject constructor() {
    fun map(from: ComicsPage): ComicsListState {
        return ComicsListState(
            currentPage = from.pageNumber,
            items = from.comics.map { comic ->
                ComicListItemState.Item(
                    id = comic.id,
                    title = comic.title,
                    imageUrl = comic.imageUrl,
                    imageExtension = comic.imageExtension
                )
            }
        )
    }
}
