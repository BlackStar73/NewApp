package com.example.newapp.data.repository

import com.example.newapp.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getArticles(search: String) = apiService.getMovies(search)
}