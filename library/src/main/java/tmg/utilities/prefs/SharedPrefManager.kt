package tmg.utilities.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

abstract class SharedPrefManager(
    private val applicationContext: Context
) {
    /**
     * Specify the preference configuration
     * Default will use the applications default shared preferences
     * Custom will allow you tp specify a custom prefs key + access mode
     */
    abstract val prefConfig: SharedPrefManagerConfig

    /**
     * Specify the preference key
     * If value is null, application default shared preferences will be used
     */
    @Deprecated(
        message = "This now does nothing. Replace this with prefConfig",
        replaceWith = ReplaceWith("prefConfig"),
        level = DeprecationLevel.ERROR
    )
    open val prefsKey: String? = null

    /**
     * Specify the preference mode
     * If prefsKey = null, this field has no effect because it reads application default
     */
    @Deprecated(
        message = "This now does nothing. Replace this with prefConfig",
        replaceWith = ReplaceWith("prefConfig"),
        level = DeprecationLevel.ERROR
    )
    open val mode: Int = Context.MODE_PRIVATE

    private val sharedPrefs: SharedPreferences
        get() = when (val config = prefConfig) {
            SharedPrefManagerConfig.Default -> getSharedPrefs(
                context = applicationContext
            )
            is SharedPrefManagerConfig.Custom -> getSharedPrefs(
                context = applicationContext,
                prefsKey = config.prefsKey,
                mode = config.mode
            )
        }

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

    @JvmName("getNullableString")
    fun getString(key: String, value: String? = null): String? {
        return sharedPrefs.getString(key, value)
    }

    fun getString(key: String, value: String): String {
        return sharedPrefs.getString(key, value) ?: ""
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

    internal companion object {

        fun getSharedPrefs(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }
        fun getSharedPrefs(context: Context, prefsKey: String, mode: Int = Context.MODE_PRIVATE): SharedPreferences {
            return context.getSharedPreferences(prefsKey, mode)
        }
    }
}

sealed class SharedPrefManagerConfig {
    data object Default: SharedPrefManagerConfig()
    data class Custom(
        val prefsKey: String,
        val mode: Int = Context.MODE_PRIVATE
    ): SharedPrefManagerConfig()
}