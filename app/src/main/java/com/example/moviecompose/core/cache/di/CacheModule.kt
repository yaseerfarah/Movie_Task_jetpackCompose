package com.example.moviecompose.core.cache.di

import android.content.Context
import androidx.room.Room
import com.example.moviecompose.BuildConfig
import com.example.moviecompose.core.cache.database.local.datastore.DataStoreHelper
import com.example.moviecompose.core.cache.database.remote.ApiEndpoints
import com.example.moviecompose.core.cache.database.remote.NetworkConnectionInterceptor
import com.example.moviecompose.core.cache.source.MovieRoomLocalDs
import com.google.gson.GsonBuilder
import com.yasser.movie_app_task.Data.Local.MainRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
 class CacheModule {

    @Provides
    @Singleton
    fun apiEndpoints(retrofit: Retrofit): ApiEndpoints {
        return retrofit.create(ApiEndpoints::class.java)
    }


    @Provides
    fun okHttp(networkConnectionInterceptor: NetworkConnectionInterceptor):OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(networkConnectionInterceptor)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES).build()

    }

    @Singleton
    @Provides
    fun retrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context: Context): MainRoomDatabase {
       return Room.databaseBuilder(
            context,
            MainRoomDatabase::class.java, BuildConfig.DB_Name
        ).build()
    }


    @Provides
    @Singleton
    fun sharedPrefs(@ApplicationContext context: Context):DataStoreHelper{
        return DataStoreHelper(context)
    }


    @Provides
    @Singleton
    fun roomImplementation(@ApplicationContext context: Context,roomDatabase: MainRoomDatabase):MovieRoomLocalDs{
        return MovieRoomLocalDs(context,roomDatabase.categoryWithMovieDao())
    }












}