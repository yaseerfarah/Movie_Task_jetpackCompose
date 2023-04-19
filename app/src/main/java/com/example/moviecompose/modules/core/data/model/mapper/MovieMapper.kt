package com.example.moviecompose.modules.core.data.model.mapper

import com.example.moviecompose.core.cache.database.local.room.dto.CategoryDto
import com.example.moviecompose.core.cache.database.local.room.dto.CategoryWithMoviesDto
import com.example.moviecompose.core.cache.database.local.room.dto.MovieDto
import com.example.moviecompose.modules.core.data.model.response.CategoryResponse
import com.example.moviecompose.modules.core.data.model.response.MovieResponse
import com.example.moviecompose.modules.core.domain.entity.CategoryEntity
import com.example.moviecompose.modules.core.domain.entity.CategoryWithMoviesEntity
import com.example.moviecompose.modules.core.domain.entity.MovieEntity


fun CategoryDto.toCategoryEntity():CategoryEntity{
    return CategoryEntity(
        id=id,
        name=name
    )
}


fun MovieDto.toMovieEntity():MovieEntity{
    return MovieEntity(
        id=id,
        original_title = original_title,
        title=title,
        overview=overview,
        vote_average=vote_average,
        adult=adult,
        poster_path=poster_path,
        backdrop_path=backdrop_path,
        popularity=popularity,
        genre_ids=genre_ids
    )
}


fun CategoryWithMoviesDto.toCategoryWithMoviesEntity():CategoryWithMoviesEntity{
    return CategoryWithMoviesEntity(
        categoryModel = categoryModel.toCategoryEntity(),
        moviesList = moviesList.map { it.toMovieEntity() }
    )

}



fun CategoryEntity.toCategoryDto():CategoryDto{
    return CategoryDto(
        id=id,
        name=name
    )
}


fun MovieEntity.toMovieDto():MovieDto{
    return MovieDto(
        id=id,
        original_title = original_title,
        title=title,
        overview=overview,
        vote_average=vote_average,
        adult=adult,
        poster_path=poster_path,
        backdrop_path=backdrop_path,
        popularity=popularity,
        genre_ids=genre_ids
    )
}


fun CategoryWithMoviesEntity.toCategoryWithMoviesDto():CategoryWithMoviesDto{
    return CategoryWithMoviesDto(
        categoryModel = categoryModel.toCategoryDto(),
        moviesList = moviesList.map { it.toMovieDto() }
    )

}



fun CategoryResponse.toCategoryEntity():CategoryEntity{
    return CategoryEntity(
        id=id,
        name=name
    )
}


fun MovieResponse.toMovieEntity():MovieEntity{
    return MovieEntity(
        id=id,
        original_title = original_title,
        title=title,
        overview=overview,
        vote_average=vote_average,
        adult=adult,
        poster_path=poster_path,
        backdrop_path=backdrop_path,
        popularity=popularity,
        genre_ids=genre_ids
    )
}
