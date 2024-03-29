package com.example.moviecompose.modules.core.data.source

import com.example.moviecompose.BuildConfig
import com.example.moviecompose.core.cache.database.remote.ApiEndpoints
import com.example.moviecompose.modules.core.data.model.response.CategoryListResponse
import com.example.moviecompose.modules.core.data.model.response.MovieListResponse
import com.example.moviecompose.modules.core.data.model.mapper.toCategoryEntity
import com.example.moviecompose.modules.core.data.model.mapper.toMovieEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryWithMoviesEntity
import com.google.gson.Gson
import javax.inject.Inject

class MovieApiSource @Inject constructor(
    private val apiEndpoints: ApiEndpoints
) {

    suspend fun getAllCategories():List<CategoryEntity>{

        val categoryList:MutableList<CategoryEntity> = mutableListOf()
        val response= apiEndpoints.getAllCategories(BuildConfig.ApiKey)
        if (response.isSuccessful){
            val categoryResponse=
                Gson().fromJson(response.body()!!.asJsonObject.toString(), CategoryListResponse::class.java)
            categoryList.addAll( categoryResponse.genres.map { it.toCategoryEntity() })
        }else{
            val message = if (response.body()?.has("status_message") == true){
                response.body()!!.get("status_message") as String
            }else{
                "Something went wrong"
            }
            Throwable(message)
        }
        return categoryList
    }



    suspend fun getAllMoviesByCategoryId(categoryModel: CategoryEntity):CategoryWithMoviesEntity?{

        var categoryWithMoviesModel:CategoryWithMoviesEntity?=null
        val response= apiEndpoints.getAllMoviesByCategoryId(BuildConfig.ApiKey,categoryId = categoryModel.id.toString())

        if (response.isSuccessful){
            val movieListResponse=Gson().fromJson(response.body()!!.asJsonObject.toString(), MovieListResponse::class.java)

            categoryWithMoviesModel= CategoryWithMoviesEntity(categoryModel,movieListResponse.results.map { it.toMovieEntity() })
        }else{
            val message = if (response.body()?.has("status_message") == true){
                response.body()!!.get("status_message") as String
            }else{
                "Something went wrong"
            }
            Throwable(message)
        }
        return categoryWithMoviesModel

    }

}