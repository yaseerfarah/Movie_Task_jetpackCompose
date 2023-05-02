package com.example.moviecompose.modules.details.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.moviecompose.modules.core.domain.entity.MovieEntity
import com.google.gson.Gson


sealed class DetailsFlow(val name:String){

    object Root:DetailsFlow("details-root"){
        val route = "$name?$ARG_MOVIE_ID={$ARG_MOVIE_ID}"

        fun route(movieEntity:MovieEntity):String{
            val movieParam=Gson().toJson(movieEntity)
            return "$name?$ARG_MOVIE_ID=${movieParam}"
        }

    }

    object DetailsScreen:DetailsFlow("details-screen"){

        val arguments = listOf(
            navArgument(ARG_MOVIE_ID) {
                type = NavType.StringType
            }
        )

        val route = "$name?$ARG_MOVIE_ID={$ARG_MOVIE_ID}"
    }

}





class MovieType : NavType<MovieEntity>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): MovieEntity? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): MovieEntity {
        return Gson().fromJson(value, MovieEntity::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: MovieEntity) {
        bundle.putParcelable(key, value)
    }
}


const val ARG_MOVIE_ID = "movieId"