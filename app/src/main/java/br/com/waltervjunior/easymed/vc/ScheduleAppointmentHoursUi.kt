package br.com.waltervjunior.easymed.vc

import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.FloatingEditText
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.component.floatingEditText
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick

class ScheduleAppointmentHoursUi : AnkoComponent<ScheduleAppointmentHours> {

    lateinit var actionBar: ActionBar

    //detalhes do médico
    lateinit var doctorCardView: CardView
    lateinit var nameTitleTextView : TextView
    lateinit var nameTextView : TextView
    lateinit var crmTitleTextView : TextView
    lateinit var crmTextView : TextView
    lateinit var streetTitleTextView : TextView
    lateinit var streetTextView : TextView
    lateinit var phoneTitleTextView : TextView
    lateinit var phoneTextView : TextView
    lateinit var cellphoneTitleTextView : TextView
    lateinit var cellphoneTextView : TextView
    lateinit var emailTitleTextView : TextView
    lateinit var emailTextView : TextView

    //convenio
    lateinit var agreement : FloatingEditText

    //data da consulta
    lateinit var dateAppointment : FloatingEditText
    lateinit var hoursRecyclerView : RecyclerView

    //botão done
    lateinit var doneButton : Button

    override fun createView(ui: AnkoContext<ScheduleAppointmentHours>) = with(ui) {
        relativeLayout {
            actionBar = actionBar {
                title.text = "Detalhes da consulta"
                show(backImageView)
                backImageView.onClick { ui.owner.onBackPressed() }
            }

            //region <! Card com informações do médico !>
            doctorCardView = cardView {
                generateViewId()

                relativeLayout {
                    padding = dip(10)

                    nameTitleTextView = textView {
                        generateViewId()
                        text = "Nome:"
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }
                    nameTextView = textView{
                        generateViewId()
                        textSize = 16f
                    }.lparams(matchParent){
                        rightOf(nameTitleTextView)
                        bottomMargin = dip(5)
                        marginStart = dip(5)
                    }

                    crmTitleTextView = textView {
                        generateViewId()
                        text = "CRM:"
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams{
                        below(nameTitleTextView)
                    }
                    crmTextView = textView {
                        generateViewId()
                        textSize = 16f
                    }.lparams(matchParent){
                        rightOf(crmTitleTextView)
                        below(nameTitleTextView)
                        bottomMargin = dip(5)
                        marginStart = dip(5)
                    }

                    streetTitleTextView = textView {
                        generateViewId()
                        textSize = 16f
                        text = "Endereço:"
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams{
                        below(crmTitleTextView)
                    }
                    streetTextView = textView {
                        generateViewId()
                        textSize = 16f
                    }.lparams(matchParent){
                        below(crmTitleTextView)
                        rightOf(streetTitleTextView)
                        bottomMargin = dip(5)
                        marginStart = dip(5)
                    }

                    phoneTitleTextView = textView{
                        generateViewId()
                        textSize = 16f
                        text = "Telefone:"
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams{
                        below(streetTitleTextView)
                    }
                    phoneTextView = textView {
                        generateViewId()
                        textSize = 16f
                    }.lparams(matchParent){
                        below(streetTitleTextView)
                        rightOf(phoneTitleTextView)
                        bottomMargin = dip(5)
                        marginStart = dip(5)
                    }

                    cellphoneTitleTextView = textView {
                        generateViewId()
                        textSize = 16f
                        text = "Celular:"
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams{
                        below(phoneTitleTextView)
                    }
                    cellphoneTextView = textView {
                        generateViewId()
                        textSize = 16f
                    }.lparams(matchParent){
                        below(phoneTitleTextView)
                        rightOf(cellphoneTitleTextView)
                        bottomMargin = dip(5)
                        marginStart = dip(5)
                    }

                    emailTitleTextView = textView {
                        generateViewId()
                        textSize = 16f
                        text = "E-mail:"
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams{
                        below(cellphoneTitleTextView)
                    }
                    emailTextView = textView {
                        generateViewId()
                        textSize = 16f
                    }.lparams(matchParent) {
                        below(cellphoneTitleTextView)
                        rightOf(emailTitleTextView)
                        marginStart = dip(5)
                    }
                }
            }.lparams{
                below(actionBar)
                margin = dip(10)
            }

            agreement = floatingEditText {
                generateViewId()
                setLabel("Convênio.")
            }.lparams(matchParent){
                below(doctorCardView)
            }

            dateAppointment = floatingEditText {
                generateViewId() 
                setLabel("Dia da consulta.")
            }.lparams(matchParent){
                below(agreement)
            }

            hoursRecyclerView = recyclerView {
                generateViewId()
            }.lparams(matchParent, dip(400)){
                below(dateAppointment)
            }

            doneButton = button {
                generateViewId()
                text = "Realizar agendamento da consulta"
            }.lparams(matchParent){
                below(hoursRecyclerView)
            }
        }
    }
}