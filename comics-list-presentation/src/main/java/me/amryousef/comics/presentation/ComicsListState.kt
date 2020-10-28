package me.amryousef.comics.presentation

data class ComicsListState(
    val currentPage: Int,
    val totalPages: Int,
    val itemsPerPage: Int,
    val items: List<Int>
)
