package com.example.moviecompose.core.cache.database.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class DataStoreHelper(private val context: Context) {




    companion object {
        private const val SHARED_PREFS_NAME = "UP_Data"
         const val HOURS_UNTIL_PROMPT = 4
         const val MILLIS_UNTIL_PROMPT = HOURS_UNTIL_PROMPT  * 60 * 60 * 1000
          val LAST_UPDATE = stringPreferencesKey("LAST_UP")
    }

    private val Context.preferenceDataStore by preferencesDataStore(
        name = SHARED_PREFS_NAME
    )



}