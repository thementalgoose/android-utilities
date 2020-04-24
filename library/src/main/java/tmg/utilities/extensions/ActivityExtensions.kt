package tmg.utilities.extensions

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.Px
import tmg.utilities.models.DeviceStatus
import tmg.utilities.models.PermissionRequestResult
import tmg.utilities.utils.ColorUtils
import tmg.utilities.utils.PermissionUtils

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


//region Soft keyboard

/**
 * Show or hide the soft keyboard
 */
fun Activity.setSoftKeyboard(show: Boolean) {
    val inputMethodManager = this.managerInputMethod
    if (show) {
        inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
    }
    else {
        currentFocus?.windowToken?.let {
            inputMethodManager.hideSoftInputFromWindow(it, 0)
        }
    }
}

//endregion

//region Permissions

/**
 * Check if a specific permission is granted
 */
fun Activity.isPermissionGranted(permission: String): Boolean {
    return PermissionUtils.getPermissionsState(this, permission).isAllGranted
}

/**
 * Check if a list of permissions have been approved
 */
fun Activity.getPermissionsState(vararg permissions: String): PermissionRequestResult {
    return PermissionUtils.getPermissionsState(this, *permissions)
}

//endregion

//region Dimensions

/**
 * Screen height, in Px
 */
@Px
fun Activity.getScreenHeight(): Int {
    return this.displayMetrics().heightPixels
}

/**
 * Screen width, in Px
 */
@Px
fun Activity.getScreenWidth(): Int {
    return this.displayMetrics().widthPixels
}

/**
 * Display metrics related to the activity
 */
fun Activity.displayMetrics(): DisplayMetrics {
    val displayMetrics = DisplayMetrics()
    windowManager?.defaultDisplay?.getMetrics(displayMetrics)
    return displayMetrics
}

//endregion

//region Device Status

/**
 * Get summary of all the device attributes
 */
val Activity.deviceStatus: DeviceStatus
    get() = DeviceStatus(this)

/**
 * Check if the WiFi is enabled
 */
val Activity.isWiFiEnabled: Boolean
    get() = managerWifi.isWifiEnabled

//endregion
