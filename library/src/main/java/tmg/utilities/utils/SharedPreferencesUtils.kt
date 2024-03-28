package tmg.utilities.utils

import android.content.Context
import android.content.SharedPreferences

@Deprecated(
    message = "We recommend extending an instance of SharedPrefManager and injecting that where applicable "
)
class SharedPreferencesUtils {
    companion object {
        private const val SHARED_PREFS_KEY = "DefaultPrefs"

        //region Primitives

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.saveString(key, value, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.saveString")
        )
        fun save(context: Context, key: String, value: String, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putString(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.saveBoolean(key, value, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.saveBoolean")
        )
        fun save(context: Context, key: String, value: Boolean, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putBoolean(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.saveInt(key, value, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.saveInt")
        )
        fun save(context: Context, key: String, value: Int, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putInt(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.saveFloat(key, value, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.saveFloat")
        )
        fun save(context: Context, key: String, value: Float, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putFloat(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.saveStringSet(key, value, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.saveStringSet")
        )
        fun save(context: Context, key: String, value: Set<String>, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putStringSet(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.saveLong(key, value, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.saveLong")
        )
        fun save(context: Context, key: String, value: Long, prefsKey: String = SHARED_PREFS_KEY) {
            edit(context, prefsKey).putLong(key, value).apply()
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getStr(key, defaultValue, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.getString")
        )
        fun getString(context: Context, key: String, defaultValue: String = "", prefsKey: String = SHARED_PREFS_KEY): String {
            return prefs(context, prefsKey).getString(key, defaultValue) ?: defaultValue
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getInt(key, defaultValue, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.getInt")
        )
        fun getInt(context: Context, key: String, defaultValue: Int = -1, prefsKey: String = SHARED_PREFS_KEY): Int {
            return prefs(context, prefsKey).getInt(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getLong(key, defaultValue, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.getLong")
        )
        fun getLong(context: Context, key: String, defaultValue: Long = -1L, prefsKey: String = SHARED_PREFS_KEY): Long {
            return prefs(context, prefsKey).getLong(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getFloat(key, defaultValue, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.getFloat")
        )
        fun getFloat(context: Context, key: String, defaultValue: Float = -1f, prefsKey: String = SHARED_PREFS_KEY): Float {
            return prefs(context, prefsKey).getFloat(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getBoolean(key, defaultValue, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.getBoolean")
        )
        fun getBoolean(context: Context, key: String, defaultValue: Boolean = false, prefsKey: String = SHARED_PREFS_KEY): Boolean {
            return prefs(context, prefsKey).getBoolean(key, defaultValue)
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getStringSet(key, defaultValue, prefsKey = \"DefaultPrefs\")", "tmg.utilities.extensions.getStringSet")
        )
        fun getSet(context: Context, key: String, defaultValue: Set<String> = setOf(), prefsKey: String = SHARED_PREFS_KEY): Set<String> {
            return prefs(context, prefsKey).getStringSet(key, defaultValue) ?: defaultValue
        }

        //endregion

        //region Utilities

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)")
        )
        fun prefs(context: Context, prefsKey: String = SHARED_PREFS_KEY): SharedPreferences {
            return context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)
        }

        @JvmStatic
        @JvmOverloads
        @Deprecated(
            message = "Replace with context extensions",
            replaceWith = ReplaceWith("context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE).edit()")
        )
        fun edit(context: Context, prefsKey: String = SHARED_PREFS_KEY): SharedPreferences.Editor {
            return prefs(context, prefsKey).edit()
        }

        //endregion
    }
}