package local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import local.entity.RequestCount
import java.time.LocalDate

@Dao
interface RequestCountDao {
    @Query("SELECT * FROM requests_by_day")
    fun getAll(): List<RequestCount>

    @Query("SELECT * FROM REQUESTS_BY_DAY WHERE request_date LIKE :date")
    fun getByDay(date : LocalDate): RequestCount
    @Insert
    fun insert(requestCount: RequestCount)
}