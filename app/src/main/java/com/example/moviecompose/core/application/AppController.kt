package com.example.moviecompose.core.application

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.moviecompose.core.cache.database.local.datastore.DataStoreHelper
import dagger.hilt.android.HiltAndroidApp

import javax.inject.Inject

@HiltAndroidApp
class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

    }



}