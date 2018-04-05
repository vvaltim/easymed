package br.com.waltervjunior.easymed.vc

import android.widget.ListView
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*

class PartialConfigurationActivityUi : AnkoComponent<PartialConfigurationActivity> {

    lateinit var actionBar : ActionBar
    lateinit var listView : ListView

    override fun createView(ui: AnkoContext<PartialConfigurationActivity>)= with(ui) {
        relativeLayout{
            actionBar = actionBar {
                title.text = "Configuração personalizada"
                show(backImageView)
            }
            listView = listView {
                generateViewId()

            }.lparams(matchParent, matchParent){
                below(actionBar)
            }
        }
    }
}