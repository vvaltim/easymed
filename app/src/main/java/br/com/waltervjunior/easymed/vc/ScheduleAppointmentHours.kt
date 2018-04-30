package br.com.waltervjunior.easymed.vc

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.setContentView

class ScheduleAppointmentHours : Activity() {
    lateinit var ui : ScheduleAppointmentHoursUi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ScheduleAppointmentHoursUi()
        ui.setContentView(this)
    }
}