package com.southerntw.safespace.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.southerntw.safespace.data.preferences.SessionPreferences
import com.southerntw.safespace.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "session")
@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideRepository(
        preferences: SessionPreferences,
        @ApplicationContext context: Context
    ) = Repository(preferences, context)
    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        context.datastore

    @Provides
    @Singleton
    fun provideSessionPreferences(dataStore: DataStore<Preferences>) = SessionPreferences(dataStore)
}