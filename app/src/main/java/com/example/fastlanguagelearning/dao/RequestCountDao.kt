package com.example.fastlanguagelearning.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fastlanguagelearning.entity.RequestCount

@Dao
interface RequestCountDao {
    @Query("SELECT * FROM requests_by_day")
    fun getAll(): List<RequestCount>

    @Query("SELECT * FROM REQUESTS_BY_DAY WHERE request_date = :date")
    fun getByDay(date : Long): RequestCount?
    @Insert
    fun insert(requestCount: RequestCount)

    @Query("DELETE FROM REQUESTS_BY_DAY WHERE request_date = :date")
    fun deleteByDay(date: Long)

    @Query("DELETE FROM REQUESTS_BY_DAY")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(requestCount: RequestCount)
}