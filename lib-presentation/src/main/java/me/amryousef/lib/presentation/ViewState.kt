package me.amryousef.lib.presentation

sealed class ViewState<out DATA> {
    data class Ready<out DATA>(val data: DATA) : ViewState<DATA>()
    object Error : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
}
