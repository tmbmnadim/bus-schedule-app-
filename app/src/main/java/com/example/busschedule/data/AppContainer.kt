package com.example.busschedule.data

import android.content.Context

interface AppContainer {
    val scheduleRepository: ScheduleRepository
}

class ScheduleAppContainer(private val context: Context) : AppContainer {
    override val scheduleRepository: ScheduleRepository by lazy {
        OfflineScheduleRepository(ScheduleDatabase.getDatabase(context).scheduleDao())
    }
}