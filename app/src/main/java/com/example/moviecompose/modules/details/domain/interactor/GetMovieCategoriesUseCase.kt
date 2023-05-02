package com.example.moviecompose.modules.details.domain.interactor

import com.example.moviecompose.base.domain.interactors.SuspendUseCase
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieCategoriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): SuspendUseCase<List<Int>, List<CategoryEntity>>() {
    override suspend fun invoke(params: List<Int>): List<CategoryEntity> {
      return movieRepository.getAllCategories().filter { params.contains(it.id) }

    }
}