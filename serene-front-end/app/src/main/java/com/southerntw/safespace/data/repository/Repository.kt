package com.southerntw.safespace.data.repository

import android.content.Context
import com.southerntw.safespace.data.preferences.SessionPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    @ApplicationContext private val context: Context
) {
}