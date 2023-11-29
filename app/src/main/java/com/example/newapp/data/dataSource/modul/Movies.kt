package com.example.newapp.data.dataSource.modul

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newapp.data.model.Result
import java.io.Serializable

@Entity(tableName = Movies.TABLE_NAME)
data class Movies(
    @PrimaryKey(autoGenerate = true) val moviesId: Int? = null,
    val adult: Boolean?,
    val backdropPath: String?,
    //val genreIds: List<Int> = emptyList(),
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?

) : Serializable {
    companion object {
        const val TABLE_NAME = "movies"
    }
}

fun Result.mapToMovies() = Movies(
    adult = this.adult,
    backdropPath = this.backdropPath,
    //genreIds = this.genreIds ?: emptyList(),
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)