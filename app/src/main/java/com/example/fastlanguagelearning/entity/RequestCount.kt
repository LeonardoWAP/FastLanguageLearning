package com.example.fastlanguagelearning.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["request_date"],  unique = true)],tableName = "requests_by_day")
data class RequestCount(
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,
    @ColumnInfo(name = "count") val count : Int,
    @ColumnInfo(name = "request_date") val requestDate : Long
)
