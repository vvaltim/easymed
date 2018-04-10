package br.com.waltervjunior.easymed.vc

import android.view.Gravity
import android.widget.ImageView
import android.widget.ListView
import android.widget.RelativeLayout
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*

class PartialConfigurationActivityUi : AnkoComponent<PartialConfigurationActivity> {

    lateinit var actionBar : ActionBar
    lateinit var saveImageView : ImageView
    lateinit var listView : ListView

    override fun createView(ui: AnkoContext<PartialConfigurationActivity>)= with(ui) {
        relativeLayout{
            actionBar = actionBar {
                title.text = "Configuração personalizada"
                show(backImageView)
                saveImageView = addActionRight(R.drawable.ic_check_white)
                show(saveImageView)
            }
            listView = listView {
                generateViewId()

            }.lparams(matchParent, matchParent){
                below(actionBar)
            }
        }
    }
}