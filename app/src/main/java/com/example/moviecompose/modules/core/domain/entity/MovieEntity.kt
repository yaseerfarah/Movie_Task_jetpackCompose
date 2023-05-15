package com.example.moviecompose.modules.core.domain.entity

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Immutable
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
