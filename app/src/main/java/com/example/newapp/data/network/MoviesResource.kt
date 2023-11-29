package com.example.newapp.data.network

sealed class MoviesResource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : MoviesResource<T>(data)
    class Success<T>(data: T?) : MoviesResource<T>(data)
    class Error<T>(message: String, data: T? = null) : MoviesResource<T>(data, message)
}