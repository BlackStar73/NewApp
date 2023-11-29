package com.example.newapp.data.dataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newapp.data.dataSource.converter.MoviesTypeConverter
import com.example.newapp.data.dataSource.modul.Movies

@Database(
    entities = [Movies::class],
    version = 2,
    exportSchema = true
)

@TypeConverters(MoviesTypeConverter::class)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    companion object {
        @Volatile
        private var INSTANCE: MoviesDataBase? = null
        fun getDatabase(context: Context): MoviesDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDataBase::class.java,
                    "movies_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}