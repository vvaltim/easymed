package br.com.waltervjunior.easymed.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.widget.*
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import org.jetbrains.anko.*

class CreateScheduleUi : AnkoComponent<CreateSchedule> {
    lateinit var actionBar: ActionBar

    private lateinit var dayOfWeekTextView : TextView
    lateinit var sundayCheckBox: CheckBox
    lateinit var mondayCheckBox: CheckBox
    lateinit var tuesdayCheckBox: CheckBox
    lateinit var wednesdayCheckBox: CheckBox
    lateinit var thursdayCheckBox: CheckBox
    lateinit var fridayCheckBox: CheckBox
    lateinit var saturdayCheckBox: CheckBox

    private lateinit var morningPeriodTextView: TextView
    private lateinit var morningConteiner : LinearLayout
    lateinit var initMorningEditText : EditText
    lateinit var finalMorningEditText : EditText

    private lateinit var afternoonPeriodTextView: TextView
    private lateinit var afternoonContainer : LinearLayout
    lateinit var initAfternoonEditText : EditText
    lateinit var finalAfternoonEditText : EditText

    private lateinit var intervalTextView: TextView
    lateinit var intervalRadioGroup : RadioGroup
    lateinit var intervalOneRadioButton: RadioButton
    lateinit var intervalTwoRadioButton : RadioButton
    lateinit var intervalThreeRadioButton: RadioButton

    private lateinit var disponibilityTextView: TextView
    lateinit var partialConfigButton : Button
    lateinit var createButton : Button

    override fun createView(ui: AnkoContext<CreateSchedule>) = with(ui) {
        relativeLayout {
            actionBar = actionBar {
                show(backImageView)
            }

            scrollView {
                relativeLayout {
                    padding = dip(10)

                    dayOfWeekTextView = textView("Dias da semana"){
                        generateViewId()
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent)
                    mondayCheckBox = checkBox("Domingo"){
                        generateViewId()
                        textColor = TextView(context).currentTextColor
                        textSize = 18f
                    }.lparams(matchParent){
                        below(dayOfWeekTextView)
                    }
                    sundayCheckBox = checkBox("Segunda-feira"){
                        generateViewId()
                        textColor = TextView(context).currentTextColor
                        textSize = 18f
                    }.lparams(wrapContent){
                        below(mondayCheckBox)
                    }
                    tuesdayCheckBox = checkBox("Terça-feira"){
                        generateViewId()
                        textColor = TextView(context).currentTextColor
                        textSize = 18f
                    }.lparams(matchParent){
                        below(sundayCheckBox)
                    }
                    wednesdayCheckBox = checkBox("Quarta-feira"){
                        generateViewId()
                        textColor = TextView(context).currentTextColor
                        textSize = 18f
                    }.lparams(matchParent){
                        below(tuesdayCheckBox)
                    }
                    thursdayCheckBox = checkBox("Quinta-feira"){
                        generateViewId()
                        textColor = TextView(context).currentTextColor
                        textSize = 18f
                    }.lparams(matchParent){
                        below(wednesdayCheckBox)
                    }
                    fridayCheckBox = checkBox("Sexta-feira"){
                        generateViewId()
                        textColor = TextView(context).currentTextColor
                        textSize = 18f
                    }.lparams(matchParent){
                        below(thursdayCheckBox)
                    }
                    saturdayCheckBox = checkBox("Sabado"){
                        generateViewId()
                        textColor = TextView(context).currentTextColor
                        textSize = 18f
                    }.lparams(matchParent){
                        below(fridayCheckBox)

                    }

                    morningPeriodTextView = textView("Período da manha"){
                        generateViewId()
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent){
                        below(saturdayCheckBox)
                        topMargin = dip(10)
                    }
                    morningConteiner = linearLayout {
                        generateViewId()

                        initMorningEditText = editText {
                            generateViewId()
                            textColor = TextView(context).currentTextColor
                            textSize = 18f
                            hint = "Hora inicial"
                            gravity = Gravity.CENTER_HORIZONTAL
                            isFocusable = false
                        }.lparams(matchParent){
                            weight = 1f
                        }
                        finalMorningEditText = editText {
                            generateViewId()
                            textColor = TextView(context).currentTextColor
                            textSize = 18f
                            hint = "Hora final"
                            gravity = Gravity.CENTER_HORIZONTAL
                            isFocusable = false
                        }.lparams(matchParent){
                            weight = 1f
                        }
                    }.lparams(matchParent){
                        below(morningPeriodTextView)
                    }

                    afternoonPeriodTextView = textView("Período da tarde"){
                        generateViewId()
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent){
                        below(morningConteiner)
                        topMargin = dip(10)
                    }
                    afternoonContainer = linearLayout {
                        generateViewId()

                        initAfternoonEditText = editText{
                            generateViewId()
                            textColor = TextView(context).currentTextColor
                            hint = "Hora inicial"
                            textSize = 18f
                            gravity = Gravity.CENTER_HORIZONTAL
                            isFocusable = false
                        }.lparams(matchParent){
                            weight = 1f
                        }
                        finalAfternoonEditText = editText{
                            generateViewId()
                            textColor = TextView(context).currentTextColor
                            hint = "Hora final"
                            textSize = 18f
                            gravity = Gravity.CENTER_HORIZONTAL
                            isFocusable = false
                        }.lparams(matchParent){
                            weight = 1f
                        }
                    }.lparams(matchParent){
                        below(afternoonPeriodTextView)
                    }

                    intervalTextView = textView("Intervalo entre consultas"){
                        generateViewId()
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent){
                        below(afternoonContainer)
                        topMargin = dip(10)
                    }
                    intervalRadioGroup = radioGroup {
                        generateViewId()

                        intervalOneRadioButton = radioButton{
                            generateViewId()
                            textColor = TextView(context).currentTextColor
                            text = "15 em 15 minutos"
                            textSize = 18f
                        }.lparams(matchParent)
                        intervalTwoRadioButton = radioButton {
                            generateViewId()
                            textColor = TextView(context).currentTextColor
                            text = "30 e 30 minutos"
                            textSize = 18f
                        }.lparams(matchParent)
                        intervalThreeRadioButton = radioButton{
                            generateViewId()
                            textColor = TextView(context).currentTextColor
                            text = "Hora em hora"
                            textSize = 18f
                        }.lparams(matchParent)
                    }.lparams(matchParent){
                        below(intervalTextView)
                    }

                    disponibilityTextView = textView("Disponibilidade"){
                        generateViewId()
                        textSize = 16f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent){
                        below(intervalRadioGroup)
                        topMargin = dip(10)
                        bottomMargin = dip(10)
                    }

                    partialConfigButton = button("Configurar parcialmente"){
                        generateViewId()
                        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                        textColor = Color.WHITE
                    }.lparams(matchParent){
                        below(disponibilityTextView)
                    }
                    createButton = button("Salvar"){
                        generateViewId()
                        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                        textColor = Color.WHITE
                    }.lparams(matchParent){
                        below(partialConfigButton)
                    }
                }
            }.lparams(matchParent, matchParent){
                below(actionBar)
            }
        }
    }
}