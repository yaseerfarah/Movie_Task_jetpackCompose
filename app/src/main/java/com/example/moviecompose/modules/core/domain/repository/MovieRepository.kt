package com.example.moviecompose.modules.core.domain.repository

import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryWithMoviesEntity

interface MovieRepository {

    suspend fun getAllCategories():List<CategoryEntity>
    suspend fun getAllMoviesByCategoryId(categoryModel:CategoryEntity): CategoryWithMoviesEntity?
}