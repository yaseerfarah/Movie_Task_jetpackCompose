package com.example.moviecompose.core.cache.source

import android.content.Context
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryDto
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryMoviePairDto
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryWithMoviesDto
import com.example.moviecompose.core.cache.database.local.room.dto.MovieDto
import com.yasser.movie_app_task.Data.Local.CategoryWithMovieDaoRoom
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


class MovieRoomLocalDs(val context: Context, val categoryWithMovieDaoRoom: CategoryWithMovieDaoRoom) {

    suspend fun clearAllData(){
        coroutineScope {
            listOf(
            async { categoryWithMovieDaoRoom.deleteAllCategories() },
            async { categoryWithMovieDaoRoom.deleteAllMovies() },
            async { categoryWithMovieDaoRoom.deleteAllRelation() }
                ).awaitAll()
        }
    }


    suspend fun saveAllData(listOfCategoryWithMoviesModel: List<CategoryWithMoviesDto>){
            listOfCategoryWithMoviesModel.map {
                categoryWithMovieDaoRoom.insertCategory(it.categoryModel)
                saveAllMoviesWithCategoryId(it.moviesList,it.categoryModel.id)
            }

    }


    suspend fun getAllCategoriesWithMovies():List<CategoryWithMoviesDto>{
        return categoryWithMovieDaoRoom.getAllCategoriesWithMovies()
    }

    suspend fun getAllCategories():List<CategoryDto>{
        return categoryWithMovieDaoRoom.getAllCategories()
    }

    suspend fun getAllMovies():List<MovieDto>{
        return categoryWithMovieDaoRoom.getAllMovies()
    }



   private suspend fun saveAllMoviesWithCategoryId(listOfMovieModels:List<MovieDto>,categoryId:Int){

       coroutineScope {
           listOfMovieModels.map {
               async { categoryWithMovieDaoRoom.insertMovie(it) }
               async { categoryWithMovieDaoRoom.insertCategoryMoviePair(CategoryMoviePairDto(categoryId = categoryId,movieId = it.id)) }
           }.awaitAll()
       }

   }


    private suspend fun saveAllCategories(listOfCategoryModel:List<CategoryDto>){

        coroutineScope {
            listOfCategoryModel.map {
                async { categoryWithMovieDaoRoom.insertCategory(it) }
            }.awaitAll()
        }

    }

}