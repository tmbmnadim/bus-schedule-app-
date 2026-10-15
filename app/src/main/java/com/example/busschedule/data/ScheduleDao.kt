package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(schedule: BusSchedule)

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM schedule WHERE id=:id")
    fun getSchedule(id: Int): Flow<BusSchedule>

    @Delete
    suspend fun delete(schedule: BusSchedule)

    @Update
    suspend fun update(schedule: BusSchedule)
}