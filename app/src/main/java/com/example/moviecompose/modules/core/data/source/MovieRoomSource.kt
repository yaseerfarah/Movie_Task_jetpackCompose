package com.example.moviecompose.modules.core.data.source

import com.example.moviecompose.core.cache.database.local.room.dto.CategoryMoviePairDto
import com.example.moviecompose.modules.core.data.model.mapper.*
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryWithMoviesEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity
import com.yasser.movie_app_task.Data.Local.CategoryWithMovieDaoRoom
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieRoomSource @Inject constructor(
    private val categoryWithMovieDaoRoom: CategoryWithMovieDaoRoom
) {

    suspend fun clearAllData(){
        coroutineScope {
            val job=Job()
            launch(job) { categoryWithMovieDaoRoom.deleteAllCategories() }
            launch(job) { categoryWithMovieDaoRoom.deleteAllMovies() }
            launch(job) { categoryWithMovieDaoRoom.deleteAllRelation() }
            job.join()
        }
    }


    suspend fun saveAllData(listOfCategoryWithMoviesModel: List<CategoryWithMoviesEntity>){
        listOfCategoryWithMoviesModel.map {
            categoryWithMovieDaoRoom.insertCategory(it.categoryModel.toCategoryDto())
            saveAllMoviesWithCategoryId(it.moviesList,it.categoryModel.id)
        }

    }


    suspend fun getAllCategoriesWithMovies():List<CategoryWithMoviesEntity>{
        return categoryWithMovieDaoRoom.getAllCategoriesWithMovies().map { it.toCategoryWithMoviesEntity() }
    }

    suspend fun getAllCategories():List<CategoryEntity>{
        return categoryWithMovieDaoRoom.getAllCategories().map { it.toCategoryEntity() }
    }

    suspend fun getAllMovies():List<MovieEntity>{
        return categoryWithMovieDaoRoom.getAllMovies().map { it.toMovieEntity() }
    }



    private suspend fun saveAllMoviesWithCategoryId(listOfMovieModels:List<MovieEntity>,categoryId:Int){
        coroutineScope {
            val job=Job()
            listOfMovieModels.map {
                launch(job) {
                    categoryWithMovieDaoRoom.insertMovie(it.toMovieDto())
                    categoryWithMovieDaoRoom.insertCategoryMoviePair(CategoryMoviePairDto(categoryId = categoryId,movieId = it.id))
                }
            }
            job.join()
        }

    }


    private suspend fun saveAllCategories(listOfCategoryModel:List<CategoryEntity>){
        coroutineScope {
            val job=Job()
            listOfCategoryModel.map {
                launch(job) { categoryWithMovieDaoRoom.insertCategory(it.toCategoryDto()) }
            }
            job.join()
        }
    }

}