package com.example.newapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.data.network.MoviesResource
import com.example.newapp.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetMoviesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _connectionStatus = MutableStateFlow(ConnectionStatus())
    val connectionStatus = _connectionStatus.asStateFlow()

    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.GetMovies -> {
                getMovies()
            }

            is HomeScreenUiEvent.SearchMovies -> {
                searchMovies(event.search)
            }

            is HomeScreenUiEvent.CheckStatus -> {
                checkStatus(event.status)
            }
        }
    }

    private fun getMovies(){
        viewModelScope.launch {

            when (val result = useCase.invoke("*")) {

                is MoviesResource.Loading -> {
                    _uiState.update { it.copy(loading = true, isEmpty = false) }
                }

                is MoviesResource.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            data = (result.data?.filter { article ->
                                article.title != null && article.title != ""
                            }) ?: emptyList(),
                            isEmpty = result.data?.isEmpty() ?: false,
                        )
                    }
                }

                is MoviesResource.Error -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = "Something went wrong!",
                            data = null,
                            isEmpty = false
                        )
                    }
                }
            }
        }
    }

    private fun checkStatus(status: Boolean) {
        _connectionStatus.update {
            it.copy(isOnline = status)
        }
    }

    private fun searchMovies(search: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(loading = true, isEmpty = false) }

            when(val result = useCase.invoke(search)) {
                is MoviesResource.Loading -> {
                    _uiState.update { it.copy(loading = true, isEmpty = false) }
                }

                is MoviesResource.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            data = (result.data?.filter { article ->
                                article.title != null && article.title != ""
                            }) ?: emptyList(),
                            isEmpty = result.data?.isEmpty() ?: false,
                        )
                    }
                }

                is MoviesResource.Error -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = "Something went wrong!",
                            data = null,
                            isEmpty = false
                        )
                    }
                }
            }
        }
    }
}