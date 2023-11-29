package com.example.newapp.domain

import com.example.newapp.data.dataSource.MoviesDao
import com.example.newapp.data.dataSource.modul.Movies
import com.example.newapp.data.dataSource.modul.mapToMovies
import com.example.newapp.data.network.MoviesResource
import com.example.newapp.data.repository.MoviesRepository
import com.example.newapp.utils.DispatchersProvider
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val moviesDao: MoviesDao,
    private val dispatcher: DispatchersProvider
) {
    suspend operator fun invoke(search: String): MoviesResource<List<Movies>> {
        return withContext(dispatcher.io) {

            val response = repository.getArticles(search)

            if (response.isSuccessful) {
                try {
                    /** insert database */
                    response.body()?.results?.map { result -> result.mapToMovies() }
                        ?.let { movies -> moviesDao.addMovies(movies) }

                    /** get database */
                    val movies = moviesDao.getAllMovies()
                    MoviesResource.Success(movies)

                } catch (e: Exception) {
                    MoviesResource.Error(e.message.toString())
                }
            } else {
                MoviesResource.Error("Something went wrong!")
            }
        }
    }
}