package com.moneymong.moneymong.data.datasource.agency

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AgencyLocalDataSourceImpl @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
): AgencyLocalDataSource {
    override suspend fun saveAgencyId(agencyId: Int) {
        userDataStorePreferences.edit { preferences ->
            preferences[AGENCY_ID] = agencyId
        }
    }

    override suspend fun fetchAgencyId(): Int {
        val flow = userDataStorePreferences.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }
            .map { preferences ->
                preferences[AGENCY_ID]
            }
        return flow.firstOrNull() ?: 0
    }

    private companion object {
        val AGENCY_ID = intPreferencesKey(name = "agencyId")
    }
}