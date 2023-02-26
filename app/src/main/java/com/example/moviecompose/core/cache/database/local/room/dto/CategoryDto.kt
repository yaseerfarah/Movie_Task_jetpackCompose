package com.example.moviecompose.core.cache.database.local.room.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Categories")
data class CategoryDto(
    @PrimaryKey
    val id: Int,
    val name: String,
): Parcelable
