package com.example.moviecompose.modules.core.di

import com.example.moviecompose.modules.core.data.repository.MovieRepositoryImp
import com.example.moviecompose.modules.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieRepositoryModule {
   @Binds
   abstract fun getMovieRepository(movieRepositoryImp: MovieRepositoryImp):MovieRepository

}