package tmg.utilities.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtils {

    companion object {
        private const val SHARED_PREFS_KEY = "DefaultPrefs"

        //region Primitives

        @JvmStatic
        @JvmOverloads
        fun save(context: Context, key: String, value: String, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putString(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        fun save(context: Context, key: String, value: Boolean, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putBoolean(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        fun save(context: Context, key: String, value: Int, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putInt(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        fun save(context: Context, key: String, value: Float, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putFloat(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        fun save(context: Context, key: String, value: Set<String>, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putStringSet(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        fun save(context: Context, key: String, value: Long, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putLong(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        fun getString(context: Context, key: String, defaultValue: String = "", prefsKey: String = SHARED_PREFS_KEY): String {
            return prefs(context, prefsKey).getString(key, defaultValue) ?: defaultValue
        }

        @JvmStatic
        @JvmOverloads
        fun getInt(context: Context, key: String, defaultValue: Int = -1, prefsKey: String = SHARED_PREFS_KEY): Int {
            return prefs(context, prefsKey).getInt(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        fun getLong(context: Context, key: String, defaultValue: Long = -1L, prefsKey: String = SHARED_PREFS_KEY): Long {
            return prefs(context, prefsKey).getLong(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        fun getFloat(context: Context, key: String, defaultValue: Float = -1f, prefsKey: String = SHARED_PREFS_KEY): Float {
            return prefs(context, prefsKey).getFloat(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        fun getBoolean(context: Context, key: String, defaultValue: Boolean = false, prefsKey: String = SHARED_PREFS_KEY): Boolean {
            return prefs(context, prefsKey).getBoolean(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        fun getSet(context: Context, key: String, defaultValue: Set<String> = setOf(), prefsKey: String = SHARED_PREFS_KEY): Set<String> {
            return prefs(context, prefsKey).getStringSet(key, defaultValue) ?: defaultValue
        }

        //endregion

        //region Utilities

        @JvmStatic
        fun prefs(context: Context, prefsKey: String = SHARED_PREFS_KEY): SharedPreferences {
            return context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)
        }

        @JvmStatic
        fun edit(context: Context, prefsKey: String = SHARED_PREFS_KEY): SharedPreferences.Editor {
            return prefs(context, prefsKey).edit()
        }

        //endregion
    }
}