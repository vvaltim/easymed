package br.com.waltervjunior.easymed.adapter

import android.content.Context
import br.com.waltervjunior.easymed.extension.setSelectableItemBackground
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class SimpleCardAdapterUi : AnkoComponent<Context>{
    companion object {
        const val ID_TITLE = 1
        const val ID_CARD = 2
    }
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        relativeLayout {
            cardView {
                id = ID_CARD

                textView {
                    setSelectableItemBackground()
                    padding = dip(10)
                    id = ID_TITLE
                }.lparams(matchParent)
            }.lparams(matchParent){
                margin = dip(5)
            }
        }
    }
}