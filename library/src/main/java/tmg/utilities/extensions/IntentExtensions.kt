package tmg.utilities.extensions

import android.content.Context
import android.content.Intent

/**
 * Start an activity from an intent
 * @param context
 */
fun Intent.startActivity(context: Context) {
    context.startActivity(this)
}