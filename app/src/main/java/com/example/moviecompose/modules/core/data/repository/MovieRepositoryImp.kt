package com.example.moviecompose.modules.core.data.repository

import com.example.moviecompose.core.cache.database.local.datastore.DataStoreHelper
import com.example.moviecompose.modules.core.data.source.MovieApiSource
import com.example.moviecompose.modules.core.data.source.MovieRoomSource
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryWithMoviesEntity
import com.example.moviecompose.modules.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class MovieRepositoryImp@Inject constructor(
    private val movieApiSource: MovieApiSource,
    private val movieRoomSource: MovieRoomSource,
    private val dataStoreHelper: DataStoreHelper
):MovieRepository {
    override suspend fun getAllCategories(): List<CategoryEntity> {
        val isDataInRoom=movieRoomSource.getAllCategories().isNotEmpty()
        val isValidateDate=checkValidateData("categories")
        return if (isValidateDate&&isDataInRoom)
            movieRoomSource.getAllCategories()
        else
            movieApiSource.getAllCategories()


    }

    override suspend fun getAllMoviesByCategoryId(categoryModel: CategoryEntity): CategoryWithMoviesEntity {
        val isDataInRoom=movieRoomSource.getAllMoviesByCategoryId(categoryModel).moviesList.isNotEmpty()
        return if (checkValidateData(categoryModel.id.toString())&&isDataInRoom)
            movieRoomSource.getAllMoviesByCategoryId(categoryModel)
        else
            movieApiSource.getAllMoviesByCategoryId(categoryModel)
    }


    private suspend fun checkValidateData(id:String):Boolean{

        val lastTime=dataStoreHelper.getData<Long>(DataStoreHelper.lastUpdateKey(id)).first()?:0
        return System.currentTimeMillis()<(lastTime+DataStoreHelper.MILLIS_UNTIL_PROMPT)


    }

}