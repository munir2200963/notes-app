package com.example.aistudy

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.aistudy.service.NotificationService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AIStudy : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(hiltWorkerFactory).build()
    }

    override fun onCreate() {
        super.onCreate()
        NotificationService.createNotificationChannel(applicationContext)
    }
}