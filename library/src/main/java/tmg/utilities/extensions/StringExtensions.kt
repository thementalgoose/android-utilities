package tmg.utilities.extensions

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.core.text.HtmlCompat
import com.google.android.material.snackbar.Snackbar
import tmg.utilities.utils.ClipboardUtils
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

//region Dates

/**
 * Convert a string to a date with a given format
 *
 * @param format The format that you expect the string in (ie. "2018-09-09".toDate("yyyy-MM-dd"))
 */
fun String.toDate(format: String): Date? {
    val sdf: SimpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
    return sdf.parse(this)
}

/**
 * Get the minutes from a time string
 *
 * Assumes the string is "HH:mm"
 */
fun String.minsPastHour(): Int {
    val resp: List<String> = this.split(":")
    if (resp.size == 2) {
        return resp[1].toIntOrNull() ?: 0
    }
    throw Exception("String is not a valid time format $this")
}

/**
 * Get the total mins
 *
 * Assumes the string is "HH:mm"
 */
fun String.minsTotal(): Int {
    val resp: List<String> = this.split(":")
    if (resp.size == 2) {
        return (resp[0].toIntOrNull() ?: 0) * 60 + (resp[1].toIntOrNull() ?: 0)
    }
    throw Exception("String is not a valid time format $this")
}

/**
 * Get the hours from a time string
 *
 * Assumes the string is "HH:mm"
 */
fun String.hours(): Int {
    val resp: List<String> = this.split(":")
    if (resp.size == 2) {
        return resp[0].toIntOrNull() ?: 0
    }
    throw Exception("String is not a valid time format $this")
}

//endregion

//region HtmlUtils

/**
 * Convert the string to HTML
 */
fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

//endregion

//region Length

/**
 * Extend a string with a given character to make it a given length
 *
 * @param char The character we will extend the string with
 * @param toLength The length of the final string
 */
fun String.extendWith(char: Char, toLength: Int): String {
    if (this.length < toLength) {
        return char + extendWith(char, toLength - 1)
    }
    else {
        return this
    }
}

//endregion

/**
 * Determine if the string is an email
 */
fun String.isEmail(): Boolean {
    return this.matches(Regex("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
}

/**
 * If the string is a URL (ie. contains '://')
 */
fun String.isUrl(): Boolean {
    return this.matches(Regex("[a-zA-z]+://[^\\s]*"))
}

/**
 * If the string is a HTTP URL (ie. Starts with http:// or https://)
 */
fun String.isHttp(): Boolean {
    return this.matches(Regex("(http|https)://[^\\s]*"))
}

//region Snackbar

/**
 * Show a given string as a snackbar message
 */
fun String.showAsSnackbar(view: View, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(view, this, length).show()
}

//endregion

//region Enums

/**
 * Convert any string value into it's enum value by a given property of the enum
 */
inline fun <reified T : Enum<T>> String.toEnum(by: (enum: T) -> String = { it.name }): T? {
    return enumValues<T>().firstOrNull { by(it) == this }
}

//endregion

//region Clipboard

/**
 * Copy a string to the clipboard
 */
fun String.copyToClipboard(context: Context, label: String = "Clipboard") {
    ClipboardUtils.copyToClipboard(context, this, label)
}

//endregion