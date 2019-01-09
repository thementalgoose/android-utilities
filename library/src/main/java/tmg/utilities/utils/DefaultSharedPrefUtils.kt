package tmg.utilities.utils

import android.content.Context
import android.content.SharedPreferences

class DefaultSharedPrefUtils(context: Context) {

    companion object {

        private fun getSharedPreferences(name: String? = null, context: Context): SharedPreferences {
            val prefsName: String = if (name.isNullOrBlank()) context.packageName else name
            return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        }

        @JvmStatic
        fun fetchBoolean(prefsName: String? = null, context: Context, name: String): Boolean {
            return getSharedPreferences(prefsName, context).getBoolean(name, false)
        }

        @JvmStatic
        fun fetchFloat(prefsName: String? = null, context: Context, name: String): Float {
            return getSharedPreferences(prefsName, context).getFloat(name, -1f)
        }

        @JvmStatic
        fun fetchInt(prefsName: String? = null, context: Context, name: String): Int {
            return getSharedPreferences(prefsName, context).getInt(name, -1)
        }

        @JvmStatic
        fun fetchLong(prefsName: String? = null, context: Context, name: String): Long {
            return getSharedPreferences(prefsName, context).getLong(name, -1)
        }

        @JvmStatic
        fun fetchString(prefsName: String? = null, context: Context, name: String): String {
            return getSharedPreferences(prefsName, context).getString(name, " ")!!
        }

        @JvmStatic
        fun writeBoolean(prefsName: String? = null, context: Context, name: String, value: Boolean) {
            val pref = getSharedPreferences(prefsName, context)
            val editor = pref.edit()
            editor.putBoolean(name, value)
            editor.apply()
        }

        @JvmStatic
        fun writeFloat(prefsName: String? = null, context: Context, name: String, value: Float) {
            val pref = getSharedPreferences(prefsName, context)
            val editor = pref.edit()
            editor.putFloat(name, value)
            editor.apply()
        }

        @JvmStatic
        fun writeInt(prefsName: String? = null, context: Context, name: String, value: Int) {
            val pref = getSharedPreferences(prefsName, context)
            val editor = pref.edit()
            editor.putInt(name, value)
            editor.apply()
        }

        @JvmStatic
        fun writeLong(prefsName: String? = null, context: Context, name: String, value: Long) {
            val pref = getSharedPreferences(prefsName, context)
            val editor = pref.edit()
            editor.putLong(name, value)
            editor.apply()
        }

        @JvmStatic
        fun writeString(prefsName: String? = null, context: Context, name: String, value: String) {
            val pref = getSharedPreferences(prefsName, context)
            val editor = pref.edit()
            editor.putString(name, value)
            editor.apply()
        }
    }
}