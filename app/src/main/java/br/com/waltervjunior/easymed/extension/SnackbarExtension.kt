package br.com.waltervjunior.easymed.extension

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by Walter on 23/03/2018.
 */

/**
 * Display the Snackbar with the [Snackbar.LENGTH_SHORT] duration.
 * @param message the message text resource.
 */
fun View.snackbar(message: Int) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
    show()
}

/**
 * Display Snackbar with the [Snackbar.LENGTH_LONG] duration.
 * @param message the message text resource.
 */
fun View.longSnackbar(message: Int) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
    show()
}

/**
 * Display the Snackbar with the [Snackbar.LENGTH_SHORT] duration.
 * @param message the message text.
 */
fun View.snackbar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
    show()
}

/**
 * Display Snackbar with the [Snackbar.LENGTH_LONG] duration.
 * @param message the message text.
 */
fun View.longSnackbar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
    show()
}

/**
 * Display the Snackbar with the [Snackbar.LENGTH_SHORT] duration.
 * @param message the message text resource.
 */
fun View.snackbar(message: Int, actionText: Int, action: (View) -> Unit) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
    setAction(actionText, action)
    show()
}

/**
 * Display Snackbar with the [Snackbar.LENGTH_LONG] duration.
 * @param message the message text resource.
 */
fun View.longSnackbar(message: Int, actionText: Int, action: (View) -> Unit) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
    setAction(actionText, action)
    show()
}

/**
 * Display the Snackbar with the [Snackbar.LENGTH_SHORT] duration.
 * @param message the message text.
 */
fun View.snackbar(message: String, actionText: String, action: (View) -> Unit) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
    setAction(actionText, action)
    show()
}

/**
 * Display Snackbar with the [Snackbar.LENGTH_LONG] duration.
 * @param message the message text.
 */
fun View.longSnackbar(message: String, actionText: String, action: (View) -> Unit) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
    setAction(actionText, action)
    show()
}