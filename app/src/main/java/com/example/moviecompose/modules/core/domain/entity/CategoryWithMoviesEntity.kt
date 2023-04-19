package com.example.moviecompose.modules.core.domain.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryWithMoviesEntity(
    var  categoryModel: CategoryEntity,
    var moviesList: List<MovieEntity>,
): Parcelable
