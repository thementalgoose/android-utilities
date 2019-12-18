package tmg.utilities.extensions

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.annotation.Px
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import tmg.utilities.utils.ColorUtils

/**
 * Get the height of the status bar that's displayed inside an activity
 */
@Px
fun Activity.statusBarHeight(): Int {
    var statusBarHeight = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        statusBarHeight = resources.getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}

/**
 * Start an activity clearing the back stack
 */
fun Activity.startActivityClearStack(intent: Intent, clearTopStack: Boolean = true) {
    if (clearTopStack) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(intent)
}


/**
 * Programatically set the status bar color
 */
fun Activity.setStatusBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ColorUtils.darken(color)
    }
}


//region Fragments


//endregion
