package tmg.utilities.models

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import tmg.utilities.enums.ScreenDensityState
import tmg.utilities.extensions.*
import tmg.utilities.utils.DeviceUtils
import java.util.*

class DeviceStatus(
    activity: Activity
) {
    val deviceManufacturer: String = Build.MANUFACTURER
    val deviceModel: String = Build.MODEL
    val deviceBrand: String = Build.BRAND
    val osVersion: String = Build.VERSION.RELEASE
    val language: String = Locale.getDefault().language
    val sdkVersion: Int = Build.VERSION.SDK_INT
    val buildVersionCodeName: String = Build.VERSION.CODENAME
    val buildVersionCodeVersion: String = Build.VERSION.RELEASE
    val fingerprint: String = Build.FINGERPRINT
    val hardware: String = Build.HARDWARE
    val screenDensity: ScreenDensityState = when (activity.resources?.displayMetrics?.densityDpi) {
        DisplayMetrics.DENSITY_LOW -> ScreenDensityState.ldpi
        DisplayMetrics.DENSITY_MEDIUM -> ScreenDensityState.mdpi
        DisplayMetrics.DENSITY_HIGH -> ScreenDensityState.hdpi
        DisplayMetrics.DENSITY_XHIGH -> ScreenDensityState.xhdpi
        DisplayMetrics.DENSITY_XXHIGH -> ScreenDensityState.xxhdpi
        DisplayMetrics.DENSITY_XXXHIGH -> ScreenDensityState.xxxhdpi
        else -> ScreenDensityState.other
    }
    val screenHeight: Int = activity.getScreenHeight()
    val screenWidth: Int = activity.getScreenWidth()
    val packageName: String = activity.packageName
    val appName: String? = activity.appName()
    val deviceIMEI: String? = DeviceUtils.getIMEI(activity)
}