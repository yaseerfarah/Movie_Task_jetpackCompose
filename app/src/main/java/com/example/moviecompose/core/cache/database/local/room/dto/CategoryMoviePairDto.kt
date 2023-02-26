package com.example.moviecompose.core.cache.database.local.room.dto

import androidx.room.Entity

@Entity(primaryKeys = ["categoryId","movieId"])
data class CategoryMoviePairDto(
     val categoryId: Int,
    val movieId: Int,
)
