package tmg.utilities.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.appcompat.app.AppCompatActivity
import tmg.utilities.models.DeviceStatus
import tmg.utilities.models.PermissionRequestResult
import tmg.utilities.utils.ClipboardUtils
import tmg.utilities.utils.ColorUtils
import tmg.utilities.utils.PermissionUtils
import java.net.MalformedURLException

/**
 * Get the height of the status bar that's displayed inside an activity
 */
@SuppressLint("InternalInsetResource")
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
 * Programatically set the status bar colour
 */
fun Activity.setStatusBarColor(@ColorInt color: Int) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = color
}

/**
 * Programmatically set the status bar color to a darker shade of the supplied colour
 */
fun Activity.setStatusBarColorDark(@ColorInt color: Int) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ColorUtils.darken(color)
}

/**
 * Programmatically set the status bar icon color to either light or dark (white or black)
 */
fun Activity.setStatusBarIconsDark() {
    this.setStatusBarIconsLight(false)
}

/**
 * Programmatically set the status bar icon color to either light or dark (white or black)
 * @param isLight
 */
fun Activity.setStatusBarIconsLight(isLight: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = if (isLight) 0 else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

//region Soft keyboard

/**
 * Show or hide the soft keyboard
 */
fun Activity.setSoftKeyboard(view: View, show: Boolean) {
    this.managerInputMethod?.let { inputMethodManager ->
        if (show) {
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
        else {
            currentFocus?.windowToken?.let {
                inputMethodManager.hideSoftInputFromWindow(it, 0)
            }
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
    get() = managerWifi?.isWifiEnabled == true

//endregion

/**
 * View a URL in the Activity
 * @param url The url to view
 */
fun Activity.viewUrl(url: String): Boolean {
    val uri = try {
        Uri.parse(url)
    } catch (e: MalformedURLException) {
        return false
    }

    val browserSelectorIntent = Intent()
        .setAction(Intent.ACTION_VIEW)
        .addCategory(Intent.CATEGORY_BROWSABLE)
        .setData(Uri.parse("https:"))
    val targetIntent = Intent()
        .setAction(Intent.ACTION_VIEW)
        .addCategory(Intent.CATEGORY_BROWSABLE)
        .setData(uri)

    targetIntent.selector = browserSelectorIntent

    return try {
        startActivity(targetIntent)
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}

fun Activity.viewWebpage(url: String): Boolean {
    return this.viewUrl(url)
}