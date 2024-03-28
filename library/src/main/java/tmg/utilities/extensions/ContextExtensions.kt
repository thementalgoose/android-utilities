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
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import androidx.annotation.ArrayRes
import androidx.annotation.DimenRes
import androidx.annotation.Discouraged
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import tmg.utilities.models.InstalledPackageModel
import tmg.utilities.prefs.SharedPrefManager
import tmg.utilities.utils.ClipboardUtils
import tmg.utilities.utils.SharedPreferencesUtils


//region Shared Preferences

// String
fun Context.saveString(key: String, value: String) {
    SharedPrefManager.getSharedPrefs(this).edit().putString(key, value).apply()
}
fun Context.saveString(key: String, value: String, prefsKey: String, mode: Int = Context.MODE_PRIVATE) {
    SharedPrefManager.getSharedPrefs(this, prefsKey, mode).edit().putString(key, value).apply()
}
// Named this way to ensure prefsKey is not used as default value while upgrading.
// Will be renamed in future release to getString()
fun Context.getStr(key: String, defaultValue: String = ""): String {
    return SharedPrefManager.getSharedPrefs(this).getString(key, defaultValue) ?: defaultValue
}
// Named this way to ensure prefsKey is not used as default value while upgrading.
// Will be renamed in future release to getString()
fun Context.getStr(key: String, defaultValue: String = "", prefsKey: String, mode: Int = Context.MODE_PRIVATE): String {
    return SharedPrefManager.getSharedPrefs(this, prefsKey, mode).getString(key, defaultValue) ?: defaultValue
}

// Boolean
fun Context.saveBoolean(key: String, value: Boolean) {
    SharedPrefManager.getSharedPrefs(this).edit().putBoolean(key, value).apply()
}
fun Context.saveBoolean(key: String, value: Boolean, prefsKey: String, mode: Int = Context.MODE_PRIVATE) {
    SharedPrefManager.getSharedPrefs(this, prefsKey, mode).edit().putBoolean(key, value).apply()
}
fun Context.getBoolean(key: String, defaultValue: Boolean = false): Boolean {
    return SharedPrefManager.getSharedPrefs(this).getBoolean(key, defaultValue)
}
fun Context.getBoolean(key: String, defaultValue: Boolean = false, prefsKey: String, mode: Int = Context.MODE_PRIVATE): Boolean {
    return SharedPrefManager.getSharedPrefs(this, prefsKey, mode).getBoolean(key, defaultValue)
}

// Float
fun Context.saveFloat(key: String, value: Float) {
    SharedPrefManager.getSharedPrefs(this).edit().putFloat(key, value).apply()
}
fun Context.saveFloat(key: String, value: Float, prefsKey: String, mode: Int = Context.MODE_PRIVATE) {
    SharedPrefManager.getSharedPrefs(this, prefsKey, mode).edit().putFloat(key, value).apply()
}
fun Context.getFloat(key: String, defaultValue: Float = -1f): Float {
    return SharedPrefManager.getSharedPrefs(this).getFloat(key, defaultValue)
}
fun Context.getFloat(key: String, defaultValue: Float = -1f, prefsKey: String, mode: Int = Context.MODE_PRIVATE): Float {
    return SharedPrefManager.getSharedPrefs(this, prefsKey, mode).getFloat(key, defaultValue)
}

// Int
fun Context.saveInt(key: String, value: Int) {
    SharedPrefManager.getSharedPrefs(this).edit().putInt(key, value).apply()
}
fun Context.saveInt(key: String, value: Int, prefsKey: String, mode: Int = Context.MODE_PRIVATE) {
    SharedPrefManager.getSharedPrefs(this, prefsKey, mode).edit().putInt(key, value).apply()
}
fun Context.getInt(key: String, defaultValue: Int = -1): Int {
    return SharedPrefManager.getSharedPrefs(this).getInt(key, defaultValue)
}
fun Context.getInt(key: String, defaultValue: Int = -1, prefsKey: String, mode: Int = Context.MODE_PRIVATE): Int {
    return SharedPrefManager.getSharedPrefs(this, prefsKey, mode).getInt(key, defaultValue)
}

// Long
fun Context.saveLong(key: String, value: Long) {
    SharedPrefManager.getSharedPrefs(this).edit().putLong(key, value).apply()
}
fun Context.saveLong(key: String, value: Long, prefsKey: String, mode: Int = Context.MODE_PRIVATE) {
    SharedPrefManager.getSharedPrefs(this, prefsKey, mode).edit().putLong(key, value).apply()
}
fun Context.getLong(key: String, defaultValue: Long = -1): Long {
    return SharedPrefManager.getSharedPrefs(this).getLong(key, defaultValue)
}
fun Context.getLong(key: String, defaultValue: Long = -1, prefsKey: String, mode: Int = Context.MODE_PRIVATE): Long {
    return SharedPrefManager.getSharedPrefs(this, prefsKey, mode).getLong(key, defaultValue)
}

// Set<String>
fun Context.saveStringSet(key: String, value: Set<String>) {
    SharedPrefManager.getSharedPrefs(this).edit().putStringSet(key, value).apply()
}
fun Context.saveStringSet(key: String, value: Set<String>, prefsKey: String, mode: Int = Context.MODE_PRIVATE) {
    SharedPrefManager.getSharedPrefs(this, prefsKey, mode).edit().putStringSet(key, value).apply()
}
fun Context.getStringSet(key: String, defaultValue: Set<String> = emptySet()): Set<String> {
    return SharedPrefManager.getSharedPrefs(this).getStringSet(key, defaultValue) ?: defaultValue
}
fun Context.getStringSet(key: String, defaultValue: Set<String> = emptySet(), prefsKey: String, mode: Int = Context.MODE_PRIVATE): Set<String> {
    return SharedPrefManager.getSharedPrefs(this, prefsKey, mode).getStringSet(key, defaultValue) ?: defaultValue
}

//endregion

//region Shared Preferences

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("saveString(key, value, prefsKey)", "tmg.utilities.extensions.saveString")
)
fun Context.save(key: String, value: String, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("saveBoolean(key, value, prefsKey)", "tmg.utilities.extensions.saveBoolean")
)
fun Context.save(key: String, value: Boolean, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("saveFloat(key, value, prefsKey)", "tmg.utilities.extensions.saveFloat")
)
fun Context.save(key: String, value: Float, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("saveInt(key, value, prefsKey)", "tmg.utilities.extensions.saveInt")
)
fun Context.save(key: String, value: Int, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("saveStringSet(key, value, prefsKey)", "tmg.utilities.extensions.saveStringSet")
)
fun Context.save(key: String, value: Set<String>, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("saveLong(key, value, prefsKey)", "tmg.utilities.extensions.saveLong")
)
fun Context.save(key: String, value: Long, prefsKey: String) {
    SharedPreferencesUtils.save(this, key, value, prefsKey)
}


@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("getStr(key, defaultValue, prefsKey)", "tmg.utilities.extensions.getStr")
)
fun Context.getString(key: String, prefsKey: String): String {
    return SharedPreferencesUtils.getString(this, key, prefsKey = prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("getInt(key, defaultValue, prefsKey)", "tmg.utilities.extensions.getInt")
)
fun Context.getInt(key: String, prefsKey: String, defaultValue: Int = -1): Int {
    return SharedPreferencesUtils.getInt(this, key, defaultValue, prefsKey = prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("getLong(key, defaultValue, prefsKey)", "tmg.utilities.extensions.getLong")
)
fun Context.getLong(key: String, prefsKey: String, defaultValue: Long = -1L): Long {
    return SharedPreferencesUtils.getLong(this, key, defaultValue, prefsKey = prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("getFloat(key, defaultValue, prefsKey)", "tmg.utilities.extensions.getFloat")
)
fun Context.getFloat(key: String, prefsKey: String, defaultValue: Float = -1f): Float {
    return SharedPreferencesUtils.getFloat(this, key, defaultValue, prefsKey = prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("getBoolean(key, defaultValue, prefsKey)", "tmg.utilities.extensions.getBoolean")
)
fun Context.getBoolean(key: String, prefsKey: String, defaultValue: Boolean = false): Boolean {
    return SharedPreferencesUtils.getBoolean(this, key, defaultValue, prefsKey = prefsKey)
}

@Deprecated(
    message = "Replace with context extensions",
    replaceWith = ReplaceWith("getStringSet(key, defaultValue, prefsKey)", "tmg.utilities.extensions.getStringSet")
)
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
val Context.managerAudio: AudioManager?
    get() = getSystemService(Context.AUDIO_SERVICE) as? AudioManager

/**
 * Connectivity manager
 */
val Context.managerConnectivity: ConnectivityManager?
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

/**
 * Window manager
 */
val Context.managerWindow: WindowManager?
    get() = getSystemService(Context.WINDOW_SERVICE) as? WindowManager

/**
 * Input method manager
 */
val Context.managerInputMethod: InputMethodManager?
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

/**
 * Clipboard Manager
 */
val Context.managerClipboard: ClipboardManager?
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager

/**
 * Sensor Manager
 */
val Context.managerSensor: SensorManager?
    get() = getSystemService(Context.SENSOR_SERVICE) as? SensorManager

/**
 * Telephony Manager
 */
val Context.managerTelephony: TelephonyManager?
    get() = getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager

/**
 * Wifi Manager
 */
val Context.managerWifi: WifiManager?
    get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager

//endregion

//region Clipboard

/**
 * Copy some text to the clipboard
 */
fun Context.copyToClipboard(
    text: String,
    label: String = "",
    copySuccessfulToastMessage: String? = null
) {
    ClipboardUtils.copyToClipboard(
        context = this,
        text = text,
        label = label,
        copySuccessfulToastMessage = copySuccessfulToastMessage
    )
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

//region Dimensions

/**
 * Get the px size for a given dp value
 */
fun Context.dimensionPx(@DimenRes id: Int) = resources.getDimensionPixelSize(id).toFloat()

//endregion

//region Popup menu

fun Context.popupMenu(anchorView: View, @MenuRes menuRes: Int, itemClicked: (id: Int) -> Boolean) {
    val popupMenu = PopupMenu(anchorView.context, anchorView)
    popupMenu.inflate(menuRes)
    popupMenu.setOnMenuItemClickListener {
        itemClicked(it.itemId)
    }
    popupMenu.show()
}

//endregion