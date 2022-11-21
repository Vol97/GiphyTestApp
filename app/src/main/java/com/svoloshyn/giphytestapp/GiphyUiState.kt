package com.svoloshyn.giphytestapp

data class GiphyUiState(
    val gifUrlsList: List<String> = emptyList(),
    val searchText: String = ""
)
