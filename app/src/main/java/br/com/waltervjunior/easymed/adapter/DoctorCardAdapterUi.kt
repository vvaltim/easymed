package br.com.waltervjunior.easymed.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import br.com.waltervjunior.easymed.extension.setSelectableItemBackground
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class DoctorCardAdapterUi : AnkoComponent<Context>{
    companion object {
        const val ID_CARD = 1
        const val ID_TITLE = 2
        const val ID_DESCRIPTION = 3
    }
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        relativeLayout {
            cardView {
                id = ID_CARD
                //setSelectableItemBackground()

                relativeLayout {
                    textView {
                        id = ID_TITLE
                        typeface = Typeface.DEFAULT_BOLD
                        textSize = 18f
                        padding = dip(10)
                    }.lparams(matchParent)
                    textView {
                        id = ID_DESCRIPTION
                        textSize = 16f
                        padding = dip(10)
                    }.lparams(matchParent){
                        below(ID_TITLE)
                    }
                }
            }.lparams(matchParent){
                margin = dip(5)
            }
        }
    }
}