package com.example.moviecompose.core.application

import android.app.Activity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import javax.inject.Inject

@HiltAndroidApp
class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

    }


}