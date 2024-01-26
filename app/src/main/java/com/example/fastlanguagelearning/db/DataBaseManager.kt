package com.example.fastlanguagelearning.db

import android.content.Context
import androidx.room.Room
import com.example.fastlanguagelearning.R

object DataBaseManager {
    private var appDatabase: RequestCountDatabase? = null
    fun getDatabase(context: Context): RequestCountDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                context.applicationContext,
                RequestCountDatabase::class.java,
                context.getString(R.string.app_database_name)
            ).fallbackToDestructiveMigration()
                .build()
        }
        return appDatabase!!
    }
}