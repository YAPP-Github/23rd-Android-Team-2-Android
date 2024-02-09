package com.moneymong.moneymong.data.datasource.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
): UserLocalDataSource {


    override suspend fun saveUserId(userId: Int) {
        userDataStorePreferences.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    override suspend fun fetchUserId(): Int {
        val flow = userDataStorePreferences.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }
            .map { preferences ->
                preferences[USER_ID]
            }
        return flow.firstOrNull() ?: 0
    }

    override suspend fun saveUserNickName(nickname: String) {
        userDataStorePreferences.edit { preferences ->
            preferences[USER_NICKNAME] = nickname
        }
    }

    override suspend fun fetchUserNickName(): String {
        val flow = userDataStorePreferences.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }
            .map { preferences ->
                preferences[USER_NICKNAME]
            }
        return flow.firstOrNull().orEmpty()
    }

    override suspend fun saveDeniedCameraPermission(isDenied: Boolean) {
        userDataStorePreferences.edit { preferences ->
            preferences[DENIED_CAMERA_PERMISSION] = isDenied
        }
    }

    override suspend fun fetchDeniedCameraPermission(): Boolean {
        val flow = userDataStorePreferences.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }
            .map { preferences ->
                preferences[DENIED_CAMERA_PERMISSION]
            }
        return flow.firstOrNull() ?: false
    }


    companion object {
        val USER_ID = intPreferencesKey("userId")
        val USER_NICKNAME = stringPreferencesKey("userNickanme")
        val DENIED_CAMERA_PERMISSION = booleanPreferencesKey("isDeniedCamera")
    }
}