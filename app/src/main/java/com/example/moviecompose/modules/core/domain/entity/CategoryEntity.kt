package com.example.moviecompose.modules.core.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryEntity(
    val id: Int,
    val name: String,
): Parcelable
