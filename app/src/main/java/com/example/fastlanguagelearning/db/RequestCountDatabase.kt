package com.example.fastlanguagelearning.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fastlanguagelearning.dao.RequestCountDao
import com.example.fastlanguagelearning.entity.RequestCount

@Database(entities = [RequestCount::class], version = 3)
abstract class RequestCountDatabase : RoomDatabase() {
    abstract fun requestCountDao(): RequestCountDao
}

