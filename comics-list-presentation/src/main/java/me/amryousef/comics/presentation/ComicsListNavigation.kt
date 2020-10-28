package me.amryousef.comics.presentation

sealed class ComicsListNavigation {
    data class Detail(val id: Long) : ComicsListNavigation()
}
