package me.amryousef.lib.domain

sealed class UseCaseResult<out OUTPUT> {
    data class Success<out OUTPUT>(val data: OUTPUT) : UseCaseResult<OUTPUT>()
    data class Error<out OUTPUT>(val exception: Exception? = null) : UseCaseResult<OUTPUT>()
}
