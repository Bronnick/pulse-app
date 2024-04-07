package com.example.pulse_app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.pulse_app.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appDatabase = Room
            .databaseBuilder(this, AppDatabase::class.java, "database")
            .build()

        appContext = this
    }

    companion object {

        lateinit var appContext: Context
            private set

        lateinit var appDatabase: AppDatabase
            private set
    }
}