package tmg.utilities.extensions

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import tmg.utilities.lifecycle.DataEvent
import tmg.utilities.lifecycle.Event


/**
 * View a URL in the Activity
 * @param url The url to view
 */
fun Fragment.viewUrl(url: String): Boolean {
    return try {
        val intent: Intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}

fun Fragment.viewWebpage(url: String): Boolean {
    return this.viewUrl(url)
}

/**
 * Start an activity but clear the back stack
 */
fun Fragment.startActivityClearStack(intent: Intent, clearTopStack: Boolean = true) {
    if (clearTopStack) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(intent)
}

/**
 * Start an activity from a fragment
 */
inline fun <reified T: Any> Fragment.startActivity(kClass: Class<T>) {
    context?.startActivity(kClass)
}

//region Lifecycle

fun <T> Fragment.observe(liveData: LiveData<T>, action: (data: T) -> Unit) {
    liveData.observe(viewLifecycleOwner, action)
}

fun <T> Fragment.observeEvent(liveData: LiveData<DataEvent<T>>, action: (data: T) -> Unit) {
    observe(liveData) {
        if (it.processEvent) {
            action(it.data)
        }
    }
}

fun Fragment.observeEvent(liveData: LiveData<Event>, action: () -> Unit) {
    observe(liveData) {
        if (it.processEvent) {
            action()
        }
    }
}

//endregion