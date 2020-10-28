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
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.value = ViewState.Loading
            val result = fetchComicsListUseCase.execute(currentPage + 1)
            _state.value = when (result) {
                is UseCaseResult.Success -> ViewState.Ready(stateMapper.map(result.data))
                is UseCaseResult.Error -> ViewState.Error
            }
        }
    }
}
