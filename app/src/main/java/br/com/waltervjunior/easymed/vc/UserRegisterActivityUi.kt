package br.com.waltervjunior.easymed.vc

import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.*
import br.com.waltervjunior.easymed.R
import org.jetbrains.anko.*

class UserRegisterActivityUi : AnkoComponent<UserRegisterActivity>{
    lateinit var actionBar : Toolbar
    lateinit var backButton: ImageView
    lateinit var nameEditText: EditText
    lateinit var cellPhoneEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var dateOfBirthEditText: EditText
    lateinit var crmEditText: EditText
    lateinit var telephoneEditText: EditText
    lateinit var addressEditText: EditText
    lateinit var createButton: Button
    lateinit var userToggleButton: ToggleButton
    lateinit var userContainer : LinearLayout
    lateinit var container : LinearLayout
    lateinit var doctorContainer : LinearLayout
    lateinit var pacientContainer : LinearLayout

    override fun createView(ui: AnkoContext<UserRegisterActivity>) = with(ui) {
        relativeLayout {
            actionBar = toolbar{
                id = View.generateViewId()

                backButton = imageView(R.drawable.ic_back_image){
                    id = View.generateViewId()
                }.lparams{
                    gravity = Gravity.START
                }

                title = "Cadastro"
                setTitleTextColor(resources.getColor(R.color.whrite))
                backgroundColorResource = R.color.colorPrimary

            }.lparams(matchParent)

            userContainer = verticalLayout {
                id = View.generateViewId()
                nameEditText = editText {
                    id = View.generateViewId()
                    hintResource = R.string.full_name
                    inputType = InputType.TYPE_CLASS_TEXT
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }

                cellPhoneEditText = editText {
                    id = View.generateViewId()
                    hintResource = R.string.cell_phone
                    inputType = InputType.TYPE_CLASS_PHONE
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }

                emailEditText = editText {
                    id = View.generateViewId()
                    hintResource = R.string.email
                    inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }

                passwordEditText = editText {
                    id = View.generateViewId()
                    hintResource = R.string.password
                    inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }.lparams(matchParent){
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }
            }.lparams(matchParent){
                below(actionBar)
            }

            userToggleButton = themedToggleButton(R.style.buttonPrimary) {
                id = View.generateViewId()
                text = resources.getString(R.string.type_register)
                textOff = resources.getString(R.string.doctor_register)
                textOn = resources.getString(R.string.pacient_register)
            }.lparams(matchParent){
                below(userContainer)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

            container = verticalLayout {
                id = View.generateViewId()

                pacientContainer = verticalLayout {
                    id = View.generateViewId()
                    visibility = View.GONE

                    dateOfBirthEditText = editText {
                        id = View.generateViewId()
                        hintResource = R.string.date_of_birth
                        inputType = InputType.TYPE_DATETIME_VARIATION_DATE
                    }.lparams(matchParent) {
                        marginStart = dip(10)
                        marginEnd = dip(10)
                    }
                }.lparams(matchParent)

                doctorContainer = verticalLayout {
                    id = View.generateViewId()
                    visibility = View.GONE

                    crmEditText = editText {
                        id = View.generateViewId()
                        hintResource = R.string.crm
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(matchParent) {
                        marginStart = dip(10)
                        marginEnd = dip(10)
                    }

                    telephoneEditText = editText {
                        id = View.generateViewId()
                        hintResource = R.string.telephone
                        inputType = InputType.TYPE_CLASS_PHONE
                    }.lparams(matchParent) {
                        marginStart = dip(10)
                        marginEnd = dip(10)
                    }

                    addressEditText = editText {
                        id = View.generateViewId()
                        hintResource = R.string.address
                        inputType = InputType.TYPE_CLASS_TEXT
                    }.lparams(matchParent) {
                        marginStart = dip(10)
                        marginEnd = dip(10)
                    }
                }.lparams(matchParent)
            }.lparams(matchParent){
                below(userToggleButton)
            }

            createButton = themedButton(R.style.buttonPrimary){
                id = View.generateViewId()
                textResource = R.string.register_account
            }.lparams(matchParent){
                below(container)
                marginStart = dip(10)
                marginEnd = dip(10)
            }

        }
    }

}