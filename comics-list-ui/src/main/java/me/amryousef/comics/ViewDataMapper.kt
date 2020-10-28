package me.amryousef.comics

import dagger.Reusable
import me.amryousef.comics.presentation.ComicListItemState
import javax.inject.Inject

@Reusable
class ViewDataMapper @Inject constructor() {
    fun from(stateItems: List<ComicListItemState>) = stateItems.map { stateItem ->
        ComicItemData(
            title = stateItem.title,
            imageUrl = stateItem.imageUrl,
            imageExtension = stateItem.imageExtension
        )
    }
}
