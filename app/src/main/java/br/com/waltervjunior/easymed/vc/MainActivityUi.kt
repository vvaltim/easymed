package br.com.waltervjunior.easymed.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.Button
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*

class MainActivityUi : AnkoComponent<MainActivity> {
    lateinit var actionBar : ActionBar
    lateinit var createScheduleButton : Button
    lateinit var criarConsultaButton: Button
    lateinit var verConsultaButton: Button
    lateinit var minhaAgenda : Button
    lateinit var sairButton: Button

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui){
        relativeLayout {
            actionBar = actionBar {
                title.text = "EasyMed"
            }

            createScheduleButton = button("Criar Agenda"){
                generateViewId()
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(actionBar)
            }

            criarConsultaButton = button(R.string.create_medical_appointment){
                generateViewId()
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(createScheduleButton)
            }

            verConsultaButton = button(R.string.view_medical_appointment){
                generateViewId()
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(criarConsultaButton)
            }

            minhaAgenda = button("Minha agenda"){
                generateViewId()
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(verConsultaButton)
            }

            sairButton = button(R.string.exit){
                generateViewId()
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.myRed))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(minhaAgenda)
            }
        }
    }
}