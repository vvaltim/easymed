package br.com.waltervjunior.easymed.vc

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ScheduleAppointmentSpecializationUi : AnkoComponent<ScheduleAppointmentSpecialization>{
    lateinit var actionBar: ActionBar
    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<ScheduleAppointmentSpecialization>) = with(ui){
        relativeLayout {
            actionBar = actionBar {
                title.text = "Especialização"
                show(backImageView)
            }

            recyclerView = recyclerView {
                generateViewId()
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            }.lparams(matchParent, matchParent){
                below(actionBar)
            }
        }
    }
}