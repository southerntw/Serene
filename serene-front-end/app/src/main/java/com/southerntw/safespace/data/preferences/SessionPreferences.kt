package com.southerntw.safespace.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class SessionPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

}