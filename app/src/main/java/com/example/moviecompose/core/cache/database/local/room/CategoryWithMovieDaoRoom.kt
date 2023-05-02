package com.yasser.movie_app_task.Data.Local

import androidx.room.*
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryDto
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryMoviePairDto
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryWithMoviesDto
import com.example.moviecompose.core.cache.database.local.room.dto.MovieDto


@Dao
interface CategoryWithMovieDaoRoom {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryModel: CategoryDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(categoryModels: List<CategoryDto>)

    @Query("SELECT * FROM Categories")
    suspend fun getAllCategories():List<CategoryDto>


    @Query("DELETE  FROM Categories")
    suspend fun deleteAllCategories()



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieModel: MovieDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movieModels: List<MovieDto>)

    @Query("SELECT * FROM Movies")
    suspend fun getAllMovies():List<MovieDto>


    @Query("SELECT * FROM Categories WHERE id Like :categoryId")
    suspend fun getAllMoviesByCategoryId(categoryId:Int):CategoryWithMoviesDto


    @Query("DELETE  FROM Movies")
    suspend fun deleteAllMovies()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategoryMoviePair(join: CategoryMoviePairDto)


    @Transaction
    @Query("SELECT * FROM Categories")
    suspend fun getAllCategoriesWithMovies(): List<CategoryWithMoviesDto>


    @Query("DELETE  FROM CategoryMoviePairDto")
    suspend fun deleteAllRelation()




}