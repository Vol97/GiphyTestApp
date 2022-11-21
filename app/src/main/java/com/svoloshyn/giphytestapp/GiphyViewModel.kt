package com.svoloshyn.giphytestapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val useCase: GiphyUseCase
): ViewModel() {

    private val _giphyScreenState = MutableStateFlow(GiphyUiState())
    val giphyScreenState: StateFlow<GiphyUiState> = _giphyScreenState.asStateFlow()

    fun getGifsBySearch(searchString: String) {
        viewModelScope.launch {
            _giphyScreenState.update {
                it.copy(gifUrlsList = useCase.getSearchGifs(searchString))
            }
        }
    }

    fun updateSearchText(searchString: String) {
        _giphyScreenState.update {
            it.copy(searchText = searchString)
        }
    }
}