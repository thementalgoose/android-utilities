package tmg.utilities.extensions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//region Starting activity

fun AppCompatActivity.startActivityClearStack(intent: Intent, bundle: Bundle? = null) {
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    if (bundle == null) {
        startActivity(intent)
    }
    else {
        startActivity(intent, bundle)
    }
}

fun AppCompatActivity.reloadAppWith(intent: Intent, bundle: Bundle? = null) {
    startActivityClearStack(intent, bundle)
}

fun AppCompatActivity.viewUrl(url: String) {
    val intent: Intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

//endregion