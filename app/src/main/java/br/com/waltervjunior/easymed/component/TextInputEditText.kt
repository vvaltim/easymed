package br.com.waltervjunior.easymed.component

import android.support.design.widget.TextInputEditText
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.skTextInputEditText() = skTextInputEditText {}
inline fun ViewManager.skTextInputEditText(theme: Int = 0, init: TextInputEditText.() -> Unit) = ankoView({ TextInputEditText(it) }, theme, init)