package me.amryousef.comics

import dagger.Reusable
import me.amryousef.comics.presentation.ComicListItemState
import javax.inject.Inject

@Reusable
class ViewDataMapper @Inject constructor() {
    fun from(stateItems: List<ComicListItemState>) = stateItems.map { stateItem ->
        when (stateItem) {
            is ComicListItemState.LoadingPlaceholder -> ComicItemData.LoadingPlaceholder
            is ComicListItemState.Item ->
                ComicItemData.Item(
                    title = stateItem.title,
                    imageUrl = stateItem.imageUrl,
                    imageExtension = stateItem.imageExtension
                )
        }
    }
}
