package tmg.utilities.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

//region Starting activity

@Deprecated("startActivity is deprecated. Use AppCompatActivity.class.startClearStack(this, with()) instead",
    ReplaceWith("KClass.startClearStack(class, with: (bundle)"))
fun AppCompatActivity.startActivityClearStack(intent: Intent, bundle: Bundle? = null) {
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    if (bundle == null) {
        startActivity(intent)
    }
    else {
        startActivity(intent, bundle)
    }
}

@Deprecated("startActivity is deprecated. Use AppCompatActivity.class.startClearStack(this, with()) instead",
    ReplaceWith("KClass.startClearStack(class, with: (bundle)")
)
fun AppCompatActivity.reloadAppWith(intent: Intent, bundle: Bundle? = null) {
    startActivityClearStack(intent, bundle)
}

/**
 * View a URL in the Activity
 * @param url The url to view
 */
fun AppCompatActivity.viewUrl(url: String) {
    val intent: Intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

//endregion