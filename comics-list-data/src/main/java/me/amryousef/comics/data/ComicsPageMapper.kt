package me.amryousef.comics.data

import dagger.Reusable
import me.amryousef.comics.data.api.ComicDataWrapper
import me.amryousef.comics.domain.Comic
import me.amryousef.comics.domain.ComicCreator
import me.amryousef.comics.domain.ComicsPage
import javax.inject.Inject

@Reusable
class ComicsPageMapper @Inject constructor() {
    fun map(apiResponse: ComicDataWrapper, pageNumber: Int): ComicsPage {
        return ComicsPage(
            pageNumber = pageNumber,
            comics = apiResponse
                .data
                ?.results
                ?.filter { it.description != null }
                ?.map { data ->
                    Comic(
                        id = data.id ?: error("Comic ID cannot be null"),
                        title = data.title ?: error("Comic title cannot be null"),
                        description = data.description ?: error("Comic description cannot be null"),
                        imageUrl = data.thumbnail?.run {
                            "$path$extension"
                        } ?: error("Comic thumbnail cannot be null"),
                        issueNumber = data.issueNumber ?: 0.0,
                        creators = data.creators?.run {
                            items?.map { creator ->
                                ComicCreator(
                                    name = creator.name!!,
                                    title = creator.role!!
                                )
                            }
                        } ?: emptyList()
                    )
                } ?: error("Api response has null results")
        )
    }
}
