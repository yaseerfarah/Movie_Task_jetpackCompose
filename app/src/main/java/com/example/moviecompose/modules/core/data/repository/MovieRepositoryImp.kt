package com.example.moviecompose.modules.core.data.repository

import com.example.moviecompose.core.cache.database.local.datastore.DataStoreHelper
import com.example.moviecompose.modules.core.data.source.MovieApiSource
import com.example.moviecompose.modules.core.data.source.MovieRoomSource
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryWithMoviesEntity
import com.example.moviecompose.modules.core.domain.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class MovieRepositoryImp@Inject constructor(
    private val movieApiSource: MovieApiSource,
    private val movieRoomSource: MovieRoomSource,
    private val dataStoreHelper: DataStoreHelper
):MovieRepository {
    override suspend fun getAllCategories(): List<CategoryEntity> {
        val isDataInRoom=movieRoomSource.getAllMovies().isNotEmpty()
       return if (checkValidateData()&&isDataInRoom)
           movieRoomSource.getAllCategories()
       else
           movieApiSource.getAllCategories()


    }

    override suspend fun getAllMoviesByCategoryId(categoryModel: CategoryEntity): CategoryWithMoviesEntity? {
       return movieApiSource.getAllMoviesByCategoryId(categoryModel)
    }


    private suspend fun checkValidateData():Boolean{
        return  coroutineScope {
            val lastTime=dataStoreHelper.getData<Long>(DataStoreHelper.LAST_UPDATE).last()?:0
            System.currentTimeMillis()<(lastTime+DataStoreHelper.MILLIS_UNTIL_PROMPT)
        }

    }

}