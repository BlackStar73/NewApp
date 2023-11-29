package com.example.newapp.data.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newapp.data.dataSource.modul.Movies

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movies: List<Movies>)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movies>
}