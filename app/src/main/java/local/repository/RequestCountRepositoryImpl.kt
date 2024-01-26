package local.repository

import domain.local.repository.RequestCountRepository
import local.dao.RequestCountDao
import local.entity.RequestCount
import java.time.LocalDate
import javax.inject.Inject


class RequestCountRepositoryImpl @Inject constructor(
    private val requestCountDao: RequestCountDao
): RequestCountRepository {
    override suspend fun getAll(): List<RequestCount> {
        return requestCountDao.getAll()
    }

    override suspend fun getByDay(date: LocalDate): RequestCount {
        return requestCountDao.getByDay(date)
    }

    override suspend fun insert(requestCount: RequestCount) {
        return requestCountDao.insert(requestCount)
    }
}