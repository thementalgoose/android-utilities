package tmg.utilities.prefs

import android.content.Context
import android.content.SharedPreferences

abstract class SharedPrefManager(
    val context: Context
) {
    /**
     * Specify the preference key
     */
    abstract val prefsKey: String

    open val mode: Int = Context.MODE_PRIVATE

    private val sharedPrefs: SharedPreferences
        get() = context.getSharedPreferences(prefsKey, mode)

    private val editor: SharedPreferences.Editor
        get() = sharedPrefs.edit()

    //region Saving methods

    fun save(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun save(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun save(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    fun save(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun save(key: String, value: Set<String>) {
        editor.putStringSet(key, value).apply()
    }

    fun getInt(key: String, value: Int = -1): Int {
        return sharedPrefs.getInt(key, value)
    }

    fun getString(key: String, value: String? = null): String? {
        return sharedPrefs.getString(key, value)
    }

    fun getLong(key: String, value: Long = -1L): Long {
        return sharedPrefs.getLong(key, value)
    }

    fun getFloat(key: String, value: Float = -1f): Float {
        return sharedPrefs.getFloat(key, value)
    }

    fun getBoolean(key: String, value: Boolean = false): Boolean {
        return sharedPrefs.getBoolean(key, value)
    }

    fun getSet(key: String, value: Set<String>): MutableSet<String> {
        return sharedPrefs.getStringSet(key, value) ?: mutableSetOf()
    }

    //endregion
}