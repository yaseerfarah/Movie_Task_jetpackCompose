package com.example.moviecompose.modules.core.di

import com.example.moviecompose.modules.core.data.repository.MovieRepositoryImp
import com.example.moviecompose.modules.core.domain.repository.MovieRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieRepositoryModule {

   abstract fun getMovieRepository(movieRepositoryImp: MovieRepositoryImp):MovieRepository

}