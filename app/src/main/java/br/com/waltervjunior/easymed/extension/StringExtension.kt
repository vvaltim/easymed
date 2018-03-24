package br.com.waltervjunior.easymed.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Walter on 24/03/2018.
 */
//fun String.decodeBase64(): String = String(Base64.decode(this, Base64.DEFAULT))

fun String.getDate(format: String): Date? = this.getDate(format, Locale.getDefault())

fun String.getDate(format: String, locale: Locale): Date? = try {
    SimpleDateFormat(format, locale).parse(this)
} catch (e: Exception) {
    null
}
fun String.withoutAccents(): String? {
    var noAccents: String = this
    val charsAccents = charArrayOf('ç', 'á', 'à', 'ã', 'â', 'ä', 'é', 'è', 'ê', 'ë', 'í', 'ì', 'î', 'ï', 'ó', 'ò', 'õ', 'ô', 'ö', 'ú', 'ù', 'û', 'ü')
    val charsNoAccents = charArrayOf('c', 'a', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u')
    for (i in charsAccents.indices) {
        noAccents = noAccents.replace(charsAccents[i], charsNoAccents[i])
        noAccents = noAccents.replace(Character.toUpperCase(charsAccents[i]), Character.toUpperCase(charsNoAccents[i]))
    }
    return noAccents
}
