package br.com.waltervjunior.easymed.adapter

import org.jetbrains.anko.*

class PartialConfigurationAdapterUi : AnkoComponent<PartialConfigurationAdapter> {
    companion object {
        const val CHECKBOX = 1
    }
    override fun createView(ui: AnkoContext<PartialConfigurationAdapter>) = with(ui) {
        relativeLayout {
            checkBox {
                id = CHECKBOX
                textSize = 16f
            }.lparams{
                margin = dip(10)
            }
        }
    }
}