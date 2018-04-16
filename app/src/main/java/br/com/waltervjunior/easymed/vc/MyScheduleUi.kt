package br.com.waltervjunior.easymed.vc

import android.support.v7.widget.RecyclerView
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MyScheduleUi : AnkoComponent<MySchedule>{
    lateinit var actionBar: ActionBar
    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<MySchedule>) = with(ui){
        relativeLayout {
            actionBar = actionBar{
                title.text = "Minha agenda"
                show(backImageView)
            }
            recyclerView = recyclerView{
                generateViewId()

            }.lparams(matchParent){
                below(actionBar)
            }
        }
    }
}