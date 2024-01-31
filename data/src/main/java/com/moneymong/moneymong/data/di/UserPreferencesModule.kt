package com.moneymong.moneymong.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "local-moneymong"
)
@InstallIn(SingletonComponent::class)
@Module
abstract class UserPreferencesModule {

    companion object {

        @Singleton
        @Provides
        fun provideUserDataStorePreferences(
            @ApplicationContext context: Context
        ): DataStore<Preferences> =
            context.userDataStore
    }
}