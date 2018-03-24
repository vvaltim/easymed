package br.com.waltervjunior.easymed.vc

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.*
import br.com.waltervjunior.easymed.R
import br.com.waltervjunior.easymed.component.ActionBar
import br.com.waltervjunior.easymed.component.actionBar
import br.com.waltervjunior.easymed.extension.generateViewId
import br.com.waltervjunior.easymed.extension.getActionBarSize
import org.jetbrains.anko.*

class UserRegisterActivityUi : AnkoComponent<UserRegisterActivity>{
    lateinit var actionBar : ActionBar
    lateinit var backButton: ImageView
    lateinit var nameEditText: EditText
    lateinit var cellPhoneEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var dateOfBirthEditText: EditText
    lateinit var genderEditText : EditText
    lateinit var crmEditText: EditText
    lateinit var telephoneEditText: EditText
    lateinit var addressEditText: EditText
    lateinit var specialistEditText: EditText
    lateinit var createButton: Button
    lateinit var userToggleButton: ToggleButton
    lateinit var userContainer : LinearLayout
    lateinit var container : LinearLayout
    lateinit var doctorContainer : LinearLayout
    lateinit var pacientContainer : LinearLayout

    override fun createView(ui: AnkoContext<UserRegisterActivity>) = with(ui) {
        relativeLayout {
            actionBar = actionBar {
                title.text = "Cadastro"
                show(backImageView)
            }.lparams(matchParent, getActionBarSize())

            userContainer = verticalLayout {
                generateViewId()
                nameEditText = editText {
                    generateViewId()
                    hintResource = R.string.full_name
                    inputType = InputType.TYPE_CLASS_TEXT
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }

                cellPhoneEditText = editText {
                    generateViewId()
                    hintResource = R.string.cell_phone
                    inputType = InputType.TYPE_CLASS_PHONE
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }

                emailEditText = editText {
                    generateViewId()
                    hintResource = R.string.email
                    inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }

                passwordEditText = editText {
                    generateViewId()
                    hintResource = R.string.password
                    inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }
            }.lparams(matchParent){
                below(actionBar)
            }

            userToggleButton = toggleButton {
                generateViewId()
                text = resources.getString(R.string.type_register)
                textOff = resources.getString(R.string.doctor_register)
                textOn = resources.getString(R.string.pacient_register)
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(userContainer)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            container = verticalLayout {
                generateViewId()

                pacientContainer = verticalLayout {
                    generateViewId()
                    padding = dip(10)
                    visibility = View.GONE

                    dateOfBirthEditText = editText {
                        generateViewId()
                        hintResource = R.string.date_of_birth
                        inputType = InputType.TYPE_DATETIME_VARIATION_DATE
                    }.lparams(matchParent)

                    genderEditText = editText {
                        generateViewId()
                        hintResource = R.string.gender
                    }.lparams(matchParent)
                }.lparams(matchParent)

                doctorContainer = verticalLayout {
                    padding = dip(10)
                    generateViewId()
                    visibility = View.GONE

                    crmEditText = editText {
                        generateViewId()
                        hintResource = R.string.crm
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(matchParent)

                    telephoneEditText = editText {
                        generateViewId()
                        hintResource = R.string.telephone
                        inputType = InputType.TYPE_CLASS_PHONE
                    }.lparams(matchParent)

                    addressEditText = editText {
                        generateViewId()
                        hintResource = R.string.address
                        inputType = InputType.TYPE_CLASS_TEXT
                    }.lparams(matchParent)

                    specialistEditText = editText{
                        generateViewId()
                        hintResource = R.string.specialist
                    }.lparams(matchParent)
                }.lparams(matchParent)
            }.lparams(matchParent){
                below(userToggleButton)
            }

            createButton = button(R.string.register_account){
                generateViewId()
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
                textColor = Color.WHITE
            }.lparams(matchParent){
                below(container)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

        }
    }

}