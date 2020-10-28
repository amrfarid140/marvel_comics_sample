package me.amryousef.comics.presentation

sealed class ComicsListEvent {
    object LoadMoreFailed : ComicsListEvent()
}
