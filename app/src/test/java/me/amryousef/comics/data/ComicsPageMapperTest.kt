package me.amryousef.comics.data

import me.amryousef.comics.data.api.ComicCreatorList
import me.amryousef.comics.data.api.ComicData
import me.amryousef.comics.data.api.ComicDataContainer
import me.amryousef.comics.data.api.ComicDataWrapper
import me.amryousef.comics.data.api.ComicThumbnailData
import org.junit.Test
import kotlin.test.assertTrue

class ComicsPageMapperTest {

    private companion object {
        val BASE_COMIC_DATA = ComicData(
            id = 12L,
            title = "",
            description = "",
            thumbnail = ComicThumbnailData("", ""),
            creators = ComicCreatorList(emptyList()),
            issueNumber = 2.2
        )
        const val PAGE_NUMBER = 1
    }

    private val subject by lazy {
        ComicsPageMapper()
    }

    @Test(expected = IllegalStateException::class)
    fun givenApiResponseWithNullComicId_WhenMap_ThenIllegalStateExceptionIsThrown() {
        val response = ComicDataWrapper(
            ComicDataContainer(
                results = listOf(
                    BASE_COMIC_DATA.copy(id = null)
                )
            )
        )
        subject.map(response, PAGE_NUMBER)
    }

    @Test(expected = IllegalStateException::class)
    fun givenApiResponseWithNullComicTitle_WhenMap_ThenIllegalStateExceptionIsThrown() {
        val response = ComicDataWrapper(
            ComicDataContainer(
                results = listOf(
                    BASE_COMIC_DATA.copy(title = null)
                )
            )
        )
        subject.map(response, PAGE_NUMBER)
    }

    @Test(expected = IllegalStateException::class)
    fun givenApiResponseWithNullComicThumbnail_WhenMap_ThenIllegalStateExceptionIsThrown() {
        val response = ComicDataWrapper(
            ComicDataContainer(
                results = listOf(
                    BASE_COMIC_DATA.copy(thumbnail = null)
                )
            )
        )
        subject.map(response, PAGE_NUMBER)
    }

    @Test(expected = IllegalStateException::class)
    fun givenApiResponseWithNullResults_WhenMap_ThenIllegalStateExceptionIsThrown() {
        val response = ComicDataWrapper(
            ComicDataContainer(
                results = null
            )
        )
        subject.map(response, PAGE_NUMBER)
    }

    @Test
    fun givenApiResponseWithNullComicCreator_WhenMap_ThenEmptyCreatorListIsReturned() {
        val response = ComicDataWrapper(
            ComicDataContainer(
                results = listOf(BASE_COMIC_DATA.copy(creators = null))
            )
        )
        val result = subject.map(response, PAGE_NUMBER)
        assertTrue(result.comics.first().creators.isEmpty())
    }
}
