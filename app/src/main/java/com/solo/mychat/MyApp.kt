package com.solo.mychat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("ApiKey: ${BuildConfig.API_KEY}")
        Timber.d("ApiHost: ${BuildConfig.API_HOST}")
    }
}