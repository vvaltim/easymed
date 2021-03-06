package br.com.waltervjunior.easymed.vc

import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ScheduleAppointmentSpecializationUi : AnkoComponent<ScheduleAppointmentSpecialization>{
    lateinit var actionBar: ActionBar
    lateinit var swipeRefreshLayout : SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<ScheduleAppointmentSpecialization>) = with(ui){
        relativeLayout {
            actionBar = actionBar {
                title.text = "Especialização"
                show(backImageView)
                backImageView.onClick { ui.owner.onBackPressed() }
            }
            swipeRefreshLayout = swipeRefreshLayout {
                generateViewId()
                setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary))

                recyclerView = recyclerView {
                    generateViewId()
                    layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                }
            }.lparams(matchParent, matchParent){
                below(actionBar)
            }
        }
    }
}