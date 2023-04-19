package com.example.moviecompose.modules.home.domain.interactor

import com.example.moviecompose.base.domain.interactors.SuspendUseCase
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): SuspendUseCase<Unit, List<CategoryEntity>>() {
    override suspend fun invoke(params: Unit): List<CategoryEntity> {
      return movieRepository.getAllCategories()
    }
}