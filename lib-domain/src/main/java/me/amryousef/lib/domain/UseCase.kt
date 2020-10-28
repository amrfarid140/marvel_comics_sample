package me.amryousef.lib.domain

interface UseCase<in INPUT, out OUTPUT> {
    suspend fun execute(input: INPUT): UseCaseResult<OUTPUT>
}
