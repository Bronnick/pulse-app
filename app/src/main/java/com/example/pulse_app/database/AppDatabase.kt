package com.example.pulse_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pulse_app.classes.HistoryItem

@Database(entities = [HistoryItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pulseDao(): PulseDao

    companion object {
        private const val DATABASE_NAME = "pulse_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}