package com.example.moviecompose.modules.movie_list.domain.interactore

import com.example.moviecompose.base.domain.interactors.SuspendUseCase
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryWithMoviesEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity
import com.example.moviecompose.modules.core.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesByCategoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): SuspendUseCase<CategoryEntity, CategoryWithMoviesEntity>() {
    override suspend fun invoke(params: CategoryEntity): CategoryWithMoviesEntity {
      return movieRepository.getAllMoviesByCategoryId(categoryModel = params)

    }
}