package com.svoloshyn.giphytestapp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.svoloshyn.giphytestapp.models.GeneralGifData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val repository: GiphyRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow(GiphyUiState())
    val searchText: StateFlow<GiphyUiState> = _searchText.asStateFlow()

    private val _searchedGifs = MutableStateFlow<PagingData<GeneralGifData>>(PagingData.empty())
    val searchedGifs = _searchedGifs

    fun updateSearchText(searchText: String) {
        _searchText.update {
            it.copy(searchText = searchText)
        }
    }

    fun searchGifs(searchText: String) {
        viewModelScope.launch {
            repository.getSearchGifs(
                item = searchText
            ).cachedIn(viewModelScope).collect {
                _searchedGifs.value = it
            }
        }
    }
}