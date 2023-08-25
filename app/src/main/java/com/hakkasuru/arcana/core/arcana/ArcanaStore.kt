package com.hakkasuru.arcana.core.arcana

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArcanaStore(context: Context) {

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile("arcana_store")
    }

    fun getBoolean(key: Preferences.Key<Boolean>, defaultValue: Boolean): Flow<Boolean> =
        dataStore.data.map {
            it[key] ?: defaultValue
        }

    suspend fun putBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit {
            it[key] = value
        }
    }

    suspend fun reset() {
        dataStore.edit {
            it.clear()
        }
    }
}