package tmg.utilities.extensions

import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * Start an activity but clear the back stack
 */
fun Fragment.startActivityClearStack(intent: Intent, clearTopStack: Boolean = true) {
    if (clearTopStack) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(intent)
}