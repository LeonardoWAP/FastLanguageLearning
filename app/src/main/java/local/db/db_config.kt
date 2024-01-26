package local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import local.dao.RequestCountDao
import local.entity.RequestCount

@Database(entities = [RequestCount::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun requestCountDao(): RequestCountDao
}