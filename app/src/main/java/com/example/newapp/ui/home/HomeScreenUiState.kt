package com.example.newapp.ui.home

import com.example.newapp.data.dataSource.modul.Movies

// Represents different states for the Home screen
data class HomeScreenUiState(
    val loading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String? = null,
    val data: List<Movies?>? = emptyList(),
)

data class ConnectionStatus(
    val isOnline: Boolean? = null
)