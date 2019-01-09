package tmg.utilities.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.SimpleDateFormat
import java.util.*

//region Dates

/**
 * Convert a string to a date with a given format
 *
 * @param format The format that you expect the string in (ie. "2018-09-09".toDate("yyyy-MM-dd"))
 */
fun String.toDate(format: String): Date {
    val sdf: SimpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
    return sdf.parse(this)
}

//endregion

//region HtmlUtils

/**
 * Convert the string to HTML
 */
fun String.fromHtml(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        return Html.fromHtml(this)
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