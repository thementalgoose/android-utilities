    package tmg.utilities.extensions

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import tmg.utilities.lifecycle.DataEvent
import tmg.utilities.lifecycle.Event

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