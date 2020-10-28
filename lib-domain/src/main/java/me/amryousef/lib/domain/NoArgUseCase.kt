package me.amryousef.lib.domain

interface NoArgUseCase<out OUTPUT> {
    suspend fun execute(): UseCaseResult<OUTPUT>
}
