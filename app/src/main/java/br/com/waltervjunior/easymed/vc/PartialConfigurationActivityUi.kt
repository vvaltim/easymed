package br.com.waltervjunior.easymed.vc

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PartialConfigurationActivityUi : AnkoComponent<PartialConfigurationActivity> {

    lateinit var actionBar : ActionBar
    lateinit var saveImageView : ImageView
    lateinit var recyclerView: RecyclerView
    //lateinit var listView : ListView

    override fun createView(ui: AnkoContext<PartialConfigurationActivity>)= with(ui) {
        relativeLayout{
            actionBar = actionBar {
                title.text = "Configuração personalizada"
                show(backImageView)
                saveImageView = addActionRight(R.drawable.ic_check_white)
                show(saveImageView)
            }
            recyclerView = recyclerView {
                generateViewId()
            }.lparams(matchParent, matchParent){
                below(actionBar)
            }
            /*listView = listView {
                generateViewId()

            }.lparams(matchParent, matchParent){
                below(actionBar)
            }*/
        }
    }
}