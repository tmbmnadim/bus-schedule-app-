package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.AppContainer
import com.example.busschedule.data.ScheduleAppContainer

class ScheduleApplication: Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        appContainer = ScheduleAppContainer(this)
        super.onCreate()
    }
}
