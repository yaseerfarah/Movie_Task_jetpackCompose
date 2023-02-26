package com.example.moviecompose.core.cache.database.local.room.dto

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryWithMoviesDto(
    @Embedded
    var  categoryModel: CategoryDto,
    @Relation(
        parentColumn = "id",
        entity = MovieDto::class,
        entityColumn = "id",
        associateBy = Junction(
            value = CategoryMoviePairDto::class,
            parentColumn = "categoryId",
            entityColumn = "movieId"
        )
    )
    var moviesList: List<MovieDto>,
): Parcelable
