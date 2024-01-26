package local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(indices = [Index(value = ["request_date"])],tableName = "requests_by_day")
data class RequestCount(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "count") val count : Int,
    @ColumnInfo(name = "request_date") val requestDate : LocalDate
)
