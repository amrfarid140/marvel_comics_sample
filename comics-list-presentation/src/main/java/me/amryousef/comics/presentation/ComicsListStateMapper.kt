package me.amryousef.comics.presentation

import dagger.Reusable
import me.amryousef.comics.domain.ComicsPage
import javax.inject.Inject

@Reusable
class ComicsListStateMapper @Inject constructor() {
    fun map(from: ComicsPage): ComicsListState {
        return ComicsListState(
            from.pageNumber,
            from.pageNumber,
            from.pageNumber,
            items = emptyList()
        )
    }
}
