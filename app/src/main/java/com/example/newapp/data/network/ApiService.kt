package com.example.newapp.data.network

import com.example.newapp.data.model.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/popular")
    suspend fun getMovies(
        @Query("q") search: String
    ): Response<Article>
}