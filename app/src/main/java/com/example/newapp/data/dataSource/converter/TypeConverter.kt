package com.example.newapp.data.dataSource.converter

import androidx.room.TypeConverter
import com.example.newapp.data.dataSource.modul.Movies
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class MoviesTypeConverter {
    @TypeConverter
    fun fromStringToMoviesList(data: String): List<Movies> {
        val listType = object : TypeToken<ArrayList<Movies>>() {}.type
        return GsonBuilder().create().fromJson(data, listType)
    }

    @TypeConverter
    fun fromNewsListTypeToString(breed: ArrayList<Movies>): String {
        return GsonBuilder().create().toJson(breed)
    }
}
