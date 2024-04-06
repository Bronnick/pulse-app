package com.example.pulse_app.di

import android.content.Context
import com.example.pulse_app.database.AppDatabase
import com.example.pulse_app.database.PulseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return AppDatabase.getInstance(applicationContext)
    }

    @Provides
    fun providePlaceDao(appDatabase: AppDatabase): PulseDao {
        return appDatabase.pulseDao()
    }
}
*/
