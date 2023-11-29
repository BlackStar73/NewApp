package com.example.newapp.di

import android.app.Application
import com.example.newapp.data.dataSource.MoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(application: Application) = MoviesDataBase.getDatabase(application)

    @Singleton
    @Provides
    fun provideMoviesDao(dataBase: MoviesDataBase) =
        dataBase.getMoviesDao()

}