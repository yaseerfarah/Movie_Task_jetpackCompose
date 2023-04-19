package com.example.moviecompose.modules.core.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
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
):Parcelable
