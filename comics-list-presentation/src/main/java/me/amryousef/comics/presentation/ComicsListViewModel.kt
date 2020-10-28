package me.amryousef.comics.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.amryousef.comics.domain.FetchComicsPageUseCase
import me.amryousef.lib.domain.UseCaseResult
import me.amryousef.lib.presentation.SingleLiveEvent
import me.amryousef.lib.presentation.ViewState
import javax.inject.Inject

class ComicsListViewModel @Inject constructor(
    private val fetchComicsListUseCase: FetchComicsPageUseCase,
    private val stateMapper: ComicsListStateMapper
) : ViewModel() {
    private val _state = MutableLiveData<ViewState<ComicsListState>>()
    val state: LiveData<ViewState<ComicsListState>> get() = _state

    private val _navigation = SingleLiveEvent<ComicsListNavigation>()
    val navigation: LiveData<ComicsListNavigation> get() = _navigation

    private val _event = SingleLiveEvent<ComicsListEvent>()
    val event: LiveData<ComicsListEvent> get() = _event

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
            when (val result = fetchComicsListUseCase.execute(currentPage + 1)) {
                is UseCaseResult.Success -> {
                    val newState = stateMapper.map(result.data)
                    _state.value = (_state.value as? ViewState.Ready)?.let {
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
                is UseCaseResult.Error -> {
                    _event.value = ComicsListEvent.LoadMoreFailed
                    _state.value = (_state.value as? ViewState.Ready)?.let {
                        ViewState.Ready(
                            data = it.data.copy(
                                items = it.data.items.filterIsInstance<ComicListItemState.Item>()
                            )
                        )
                    }
                }
            }
        }
    }

    fun onItemSelected(position: Int) {
        (_state.value as? ViewState.Ready)?.let { readyState ->
            (readyState.data.items[position] as? ComicListItemState.Item)?.let { item ->
                _navigation.value = ComicsListNavigation.Detail(item.id)
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
