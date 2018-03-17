package br.com.waltervjunior.easymed.vc

import android.view.Gravity
import android.widget.Button
import br.com.waltervjunior.easymed.R
import org.jetbrains.anko.*

class MainActivityUi : AnkoComponent<MainActivity> {
    lateinit var criarConsultaButton: Button
    lateinit var verConsultaButton: Button
    lateinit var sairButton: Button

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui){
        verticalLayout {
            imageView(R.drawable.icon).lparams(dip(150),dip(150)){gravity = Gravity.CENTER_HORIZONTAL}
            criarConsultaButton = themedButton(R.style.buttonPrimary){
                textResource = R.string.create_medical_appointment
            }.lparams(matchParent)

            verConsultaButton = themedButton(R.style.buttonPrimary){
                textResource = R.string.view_medical_appointment
            }.lparams(matchParent)

            sairButton = themedButton(R.style.buttonPrimary){
                textResource = R.string.exit
            }.lparams(matchParent)
        }
    }
}