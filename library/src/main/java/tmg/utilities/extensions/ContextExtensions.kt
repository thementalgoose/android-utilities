package tmg.utilities.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.text.Spanned
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import io.reactivex.Observable
import tmg.utilities.models.InstalledPackageModel
import tmg.utilities.utils.SharedPreferencesUtils
import tmg.utilities.utils.ongoing
import kotlin.reflect.KClass

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
    return SharedPreferencesUtils.getString(this, key, prefsKey)
}

fun Context.getInt(key: String, prefsKey: String): Int {
    return SharedPreferencesUtils.getInt(this, key, prefsKey)
}

fun Context.getLong(key: String, prefsKey: String): Long {
    return SharedPreferencesUtils.getLong(this, key, prefsKey)
}

fun Context.getFloat(key: String, prefsKey: String): Float {
    return SharedPreferencesUtils.getFloat(this, key, prefsKey)
}

fun Context.getBoolean(key: String, prefsKey: String): Boolean {
    return SharedPreferencesUtils.getBoolean(this, key, prefsKey)
}

fun Context.getSet(key: String, prefsKey: String): Set<String> {
    return SharedPreferencesUtils.getSet(this, key, prefsKey)
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

fun <T: Any> Context.startActivity(kClass: KClass<T>) {
    startActivity(Intent(this, kClass::class.java))
}

fun <T: Any> Context.startActivity(kClass: Class<T>) {
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
