package com.moneymong.moneymong.data.datasource.login

import android.content.Context
import android.provider.SyncStateContract.Constants
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.moneymong.moneymong.network.api.login.AccessTokenApi
import com.moneymong.moneymong.network.request.login.RefreshTokenRequest
import com.moneymong.moneymong.network.response.login.RefreshTokenResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = "jwt")

class LoginLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : LoginLocalDataSource {
    private val accessToken = stringPreferencesKey("ACCESS_TOKEN")
    private val refreshToken = stringPreferencesKey("REFRESH_TOKEN")
    private val loginSuccess = booleanPreferencesKey("LOGIN_SUCCESS")
    private val schoolInfoExist = booleanPreferencesKey("SCHOOL_INFO_EXIST")

    override suspend fun getRefreshToken(): String {
        val preferences = context.dataStore.data.first()
        return preferences[refreshToken] ?: ""
    }

    override suspend fun getAccessToken(): String {
        val preferences = context.dataStore.data.first()
        return preferences[accessToken] ?: ""
    }

    override suspend fun updateTokens(aToken: String, rToken: String) {
        context.dataStore.edit { preferences ->
            preferences[accessToken] = aToken
            preferences[refreshToken] = rToken
        }
    }

    override suspend fun updateAccessToken(aToken: String) {
        context.dataStore.edit { preferences ->
            preferences[accessToken] = aToken
        }
    }

    override suspend fun getSchoolInfo(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[schoolInfoExist] ?: false
    }

    override suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(accessToken)
            preferences.remove(refreshToken)
            preferences.remove(loginSuccess)
            preferences.remove(schoolInfoExist)
        }
    }

    suspend fun setDataStore(aToken: String, rToken: String, success: Boolean, infoExist: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[accessToken] = aToken
            preferences[refreshToken] = rToken
            preferences[loginSuccess] = success
            preferences[schoolInfoExist] = infoExist
        }
    }
}

