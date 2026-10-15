package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getAllScheduleStream(): Flow<List<BusSchedule>>

    fun getOneScheduleStream(id: Int): Flow<BusSchedule?>

    suspend fun insertSchedule(schedule: BusSchedule)

    suspend fun updateSchedule(schedule: BusSchedule)

    suspend fun deleteSchedule(schedule: BusSchedule)
}

