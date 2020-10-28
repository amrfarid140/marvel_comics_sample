package me.amryousef.comics.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.amryousef.comics.domain.FetchComicsPageUseCase
import me.amryousef.lib.domain.UseCaseResult
import me.amryousef.lib.presentation.ViewState
import javax.inject.Inject

class ComicsListViewModel @Inject constructor(
    private val fetchComicsListUseCase: FetchComicsPageUseCase,
    private val stateMapper: ComicsListStateMapper
) : ViewModel() {
    private val _state = MutableLiveData<ViewState<ComicsListState>>()
    val state: LiveData<ViewState<ComicsListState>> get() = _state

    private val currentPage: Int
        get() {
            return (_state.value as? ViewState.Ready)?.data?.currentPage ?: 0
        }

    init {
        _state.value = ViewState.Loading
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            addLoadingPlaceHolder()
            val result = fetchComicsListUseCase.execute(currentPage + 1)
            _state.value = when (result) {
                is UseCaseResult.Success -> {
                    val newState = stateMapper.map(result.data)
                    (_state.value as? ViewState.Ready)?.let {
                        ViewState.Ready(
                            data = it.data.copy(
                                items = mutableListOf<ComicListItemState>().apply {
                                    addAll(it.data.items.filterIsInstance<ComicListItemState.Item>())
                                    addAll(newState.items)
                                },
                                currentPage = newState.currentPage
                            )
                        )
                    } ?: ViewState.Ready(newState)
                }
                is UseCaseResult.Error -> ViewState.Error
            }
        }
    }

    private fun addLoadingPlaceHolder() {
        (_state.value as? ViewState.Ready)?.let {
            _state.value = ViewState.Ready(
                data = it.data.copy(
                    items = mutableListOf<ComicListItemState>().apply {
                        addAll(it.data.items)
                        add(ComicListItemState.LoadingPlaceholder)
                    },
                )
            )
        }
    }
}
