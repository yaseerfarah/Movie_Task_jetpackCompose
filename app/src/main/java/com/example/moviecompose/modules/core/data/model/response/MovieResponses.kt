package com.example.moviecompose.modules.core.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CategoryListResponse(
    var genres:List<CategoryResponse>
)


data class MovieListResponse(
    var results:List<MovieResponse>
)


data class CategoryResponse(
    val id: Int,
    val name: String,
)



data class MovieResponse(
    val id: Int,
    val original_title: String,
    val title: String,
    val overview: String,
    val vote_average: String,
    val adult: String,
    val poster_path: String,
    val backdrop_path: String?,
    val popularity: String,
    val genre_ids: List<Int>,
)
