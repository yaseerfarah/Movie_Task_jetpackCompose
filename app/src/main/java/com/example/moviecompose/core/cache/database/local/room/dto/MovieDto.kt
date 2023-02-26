package com.example.moviecompose.core.cache.database.local.room.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Movies")
data class MovieDto(
    @PrimaryKey
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
