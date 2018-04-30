package br.com.waltervjunior.easymed.vc

import android.view.View
import br.com.waltervjunior.easymed.component.actionBar
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.sdk25.coroutines.onClick

class ScheduleAppointmentHoursUi : AnkoComponent<ScheduleAppointmentHours> {
    override fun createView(ui: AnkoContext<ScheduleAppointmentHours>) = with(ui) {
        relativeLayout {
            actionBar {
                title.text = "Detalhes da consulta"
                show(backImageView)
                backImageView.onClick { ui.owner.onBackPressed() }
            }
        }
    }
}