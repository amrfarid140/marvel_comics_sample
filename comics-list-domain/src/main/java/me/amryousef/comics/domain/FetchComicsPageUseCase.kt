package me.amryousef.comics.domain

import me.amryousef.lib.domain.UseCase
import me.amryousef.lib.domain.UseCaseResult
import javax.inject.Inject

class FetchComicsPageUseCase @Inject constructor(
    private val comicsRepository: ComicsRepository
) : UseCase<Int, ComicsPage> {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun execute(input: Int): UseCaseResult<ComicsPage> = try {
        UseCaseResult.Success(comicsRepository.fetchComicsPage(input))
    } catch (exception: Exception) {
        UseCaseResult.Error(exception)
    }
}
