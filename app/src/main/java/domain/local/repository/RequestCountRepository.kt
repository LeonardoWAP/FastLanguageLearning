package domain.local.repository

import local.entity.RequestCount
import java.time.LocalDate

interface RequestCountRepository {
   suspend fun getAll(): List<RequestCount>

    suspend fun getByDay(date : LocalDate): RequestCount

    suspend fun insert(requestCount: RequestCount)
}