package com.svoloshyn.giphytestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val useCase: GiphyUseCase
): ViewModel() {

    private val _trendingGifsUrls = MutableLiveData<List<String>>()
    val trendingGifsUrls: LiveData<List<String>>
        get() = _trendingGifsUrls

    init {
        viewModelScope.launch {
            _trendingGifsUrls.value = useCase.getTrendingGifs()
        }
    }
}