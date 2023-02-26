package com.yasser.movie_app_task.Data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviecompose.core.cache.database.local.room.RoomTypeConverter
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryDto
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryMoviePairDto
import com.example.moviecompose.core.cache.database.local.room.dto.MovieDto

@Database(entities = [CategoryDto::class, CategoryMoviePairDto::class, MovieDto::class],version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class MainRoomDatabase: RoomDatabase() {
    abstract fun categoryWithMovieDao():CategoryWithMovieDaoRoom
}