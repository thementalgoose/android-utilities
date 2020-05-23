package tmg.utilities.extensions

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.hardware.SensorManager
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.Spanned
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import io.reactivex.rxjava3.core.Observable
import tmg.utilities.models.InstalledPackageModel
import tmg.utilities.utils.ClipboardUtils
import tmg.utilities.utils.SharedPreferencesUtils
import tmg.utilities.utils.ongoing


//region Shared Preferences

fun Context.save(key: String, value: String, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

fun Context.save(key: String, value: Boolean, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

fun Context.save(key: String, value: Float, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

fun Context.save(key: String, value: Int, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

fun Context.save(key: String, value: Set<String>, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

fun Context.save(key: String, value: Long, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

fun Context.getString(key: String, prefsKey: String): String {
    return SharedPreferencesUtils.getString(this, key, prefsKey = prefsKey)
}

fun Context.getInt(key: String, prefsKey: String, defaultValue: Int = -1): Int {
    return SharedPreferencesUtils.getInt(this, key, defaultValue, prefsKey = prefsKey)
}

fun Context.getLong(key: String, prefsKey: String, defaultValue: Long = -1L): Long {
    return SharedPreferencesUtils.getLong(this, key, defaultValue, prefsKey = prefsKey)
}

fun Context.getFloat(key: String, prefsKey: String, defaultValue: Float = -1f): Float {
    return SharedPreferencesUtils.getFloat(this, key, defaultValue, prefsKey = prefsKey)
}

fun Context.getBoolean(key: String, prefsKey: String, defaultValue: Boolean = false): Boolean {
    return SharedPreferencesUtils.getBoolean(this, key, defaultValue, prefsKey = prefsKey)
}

fun Context.getSet(key: String, prefsKey: String, defaultValue: Set<String> = setOf()): Set<String> {
    return SharedPreferencesUtils.getSet(this, key, defaultValue, prefsKey = prefsKey)
}

//endregion

//region Resources

fun Context.getStringList(@ArrayRes resource: Int): List<String> {
    return this.resources.getStringArray(resource).toList()
}

fun Context.getStringArray(@ArrayRes resource: Int): Array<String> {
    return this.resources.getStringArray(resource)
}

//endregion

//region Activity

inline fun <reified T: Any> Context.startActivity(kClass: Class<T>) {
    startActivity(Intent(this, kClass::class.java))
}

//endregion

//region HTML

fun Context.getHtml(@StringRes id: Int): Spanned {
    return getString(id).fromHtml()
}

fun Context.getHtml(value: String): Spanned {
    return value.fromHtml()
}

//endregion

//region Installed apps

fun Context.installedPackages(flags: Int? = null): List<InstalledPackageModel> {
    val intent = Intent(Intent.ACTION_MAIN, null)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    val pmList = packageManager.queryIntentActivities(intent, flags ?: 0)
    return pmList.map {
        InstalledPackageModel(packageManager, it)
    }
}

fun Context.installedPackagesObservable(): Observable<List<InstalledPackageModel>> {
    return ongoing { installedPackages() }
}

//endregion

//region Dark Mode

/**
 * Check if the preference from the OS is in "night" mode
 * @param ifUndefinedDefaultTo Defaults to false (ie. it's day mode)
 */
fun Context.isInNightMode(ifUndefinedDefaultTo: Boolean = false): Boolean {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        else -> ifUndefinedDefaultTo
    }
}
/**
 * Check if the preference from the OS is in "day" mode
 * @param ifUndefinedDefaultTo Defaults to true (ie. it's day mode)
 */
fun Context.isInDayMode(ifUndefinedDefaultTo: Boolean = true): Boolean {
    return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> false
        Configuration.UI_MODE_NIGHT_NO -> true
        else -> ifUndefinedDefaultTo
    }
}

//endregion

//region Package info

/**
 * Get the package info
 */
fun Context.packageInfo(): PackageInfo {
    return packageManager.getPackageInfo(packageName, 0)
}

/**
 * Get the application name as displayed in the launcher
 */
fun Context.appName(): String? {
    var applicationInfo: ApplicationInfo? = null
    try {
        applicationInfo = packageManager.getApplicationInfo(this.applicationInfo.packageName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ((if (applicationInfo != null) packageManager.getApplicationLabel(applicationInfo) else null) as? String)
}

//endregion

//region Services

/**
 * Audio Manager
 */
val Context.managerAudio: AudioManager
    get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager

/**
 * Connectivity manager
 */
val Context.managerConnectivity: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

/**
 * Window manager
 */
val Context.managerWindow: WindowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

/**
 * Input method manager
 */
val Context.managerInputMethod: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

/**
 * Clipboard Manager
 */
val Context.managerClipboard: ClipboardManager
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

/**
 * Sensor Manager
 */
val Context.managerSensor: SensorManager
    get() = getSystemService(Context.SENSOR_SERVICE) as SensorManager

/**
 * Telephony Manager
 */
val Context.managerTelephony: TelephonyManager
    get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

/**
 * Wifi Manager
 */
val Context.managerWifi: WifiManager
    get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

//endregion

//region Clipboard

/**
 * Copy some text to the clipboard
 */
fun Context.copyToClipboard(text: String, label: String = "Clipboard") {
    ClipboardUtils.copyToClipboard(this, text, label)
}

//endregion

//region Widgets

/**
 * Update all widgets that are active of a specified provider
 */
inline fun <reified T : AppWidgetProvider> Context.updateWidgets() {

    val manager = AppWidgetManager.getInstance(this)
    val ids = manager.getAppWidgetIds(ComponentName(this, T::class.java))

    val intent = Intent(this, T::class.java)
    intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)

    sendBroadcast(intent)
}

/**
 * Update a specific widget by it's app widget id
 */
inline fun <reified T : AppWidgetProvider> Context.updateWidget(widgetId: Int) {
    val intent = Intent(this, T::class.java)
    intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, IntArray(1) { widgetId })

    sendBroadcast(intent)
}

//endregion