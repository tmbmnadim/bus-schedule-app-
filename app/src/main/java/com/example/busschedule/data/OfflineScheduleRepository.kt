package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val itemDao: ScheduleDao) : ScheduleRepository {
    override fun getAllScheduleStream(): Flow<List<BusSchedule>> = itemDao.getAll()

    override fun getOneScheduleStream(id: Int): Flow<BusSchedule?> = itemDao.getSchedule(id)

    override suspend fun insertSchedule(schedule: BusSchedule) = itemDao.insert(schedule)

    override suspend fun deleteSchedule(schedule: BusSchedule) = itemDao.delete(schedule)

    override suspend fun updateSchedule(schedule: BusSchedule) = itemDao.update(schedule)
}