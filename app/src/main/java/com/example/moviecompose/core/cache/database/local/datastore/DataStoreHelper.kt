package com.example.moviecompose.core.cache.database.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreHelper(private val context: Context) {




    companion object {
       private val Context.preferenceDataStore by preferencesDataStore(
            name = DataStoreHelper.SHARED_PREFS_NAME
        )
        const val SHARED_PREFS_NAME = "UP_Data"
         const val HOURS_UNTIL_PROMPT = 4
         const val MILLIS_UNTIL_PROMPT = HOURS_UNTIL_PROMPT  * 60 * 60 * 1000
          fun lastUpdateKey(id:String):Preferences.Key<Long> = longPreferencesKey("LAST_UP-${id}")

    }





    fun <T> getData(key: Preferences.Key<T>):Flow<T?>{
        return context.preferenceDataStore.data.map {preferences ->
            preferences[key]
        }
    }

    suspend fun <T> putData(key: Preferences.Key<T>,value: T){
         context.preferenceDataStore.edit { preferences ->
            preferences[key]=value
        }
    }

}