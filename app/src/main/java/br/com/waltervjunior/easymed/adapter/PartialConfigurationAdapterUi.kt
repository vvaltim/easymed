package br.com.waltervjunior.easymed.adapter

import android.content.Context
import org.jetbrains.anko.*

class PartialConfigurationAdapterUi : AnkoComponent<Context> {
    companion object {
        const val CHECKBOX = 1
    }
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        relativeLayout {
            checkBox {
                id = CHECKBOX
                isChecked = true
                textSize = 16f
            }.lparams(matchParent){
                margin = dip(10)
            }
        }
    }
}