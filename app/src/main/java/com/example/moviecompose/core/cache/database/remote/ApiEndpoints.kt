package com.example.moviecompose.core.cache.database.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {


    @GET("genre/movie/list")
    suspend fun getAllCategories(@Query("api_key")apiKey:String): Response<JsonObject>


    @GET("discover/movie")
    suspend fun getAllMoviesByCategoryId(@Query("api_key")apiKey:String,@Query("with_genres")categoryId:String): Response<JsonObject>





}