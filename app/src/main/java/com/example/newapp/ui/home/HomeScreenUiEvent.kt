package com.example.newapp.ui.home

sealed class HomeScreenUiEvent {
    data class SearchMovies(val search: String) : HomeScreenUiEvent()
    object GetMovies : HomeScreenUiEvent()

    data class CheckStatus(val status: Boolean) : HomeScreenUiEvent()
}