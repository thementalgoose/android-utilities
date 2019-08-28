package tmg.utilities.models

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import tmg.utilities.BuildConfig

data class InstalledPackageModel(
    private val packageManager: PackageManager,
    private val packageInfo: PackageInfo,
    val appName: String,
    val label: String,
    val packageName: String,
    val versionName: String,
    val versionCode: Long,
    val iconRes: Int,
    val isSystem: Boolean
) {
    constructor(
        packageManager: PackageManager,
        packageInfo: PackageInfo
    ) : this(
        packageManager = packageManager,
        packageInfo = packageInfo,
        appName = packageInfo.applicationInfo.loadLabel(packageManager).toString(),
        label = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString(),
        packageName = packageInfo.packageName,
        versionName = packageInfo.versionName,
        versionCode = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) packageInfo.longVersionCode else packageInfo.versionCode.toLong(),
        iconRes = packageInfo.applicationInfo.icon,
        isSystem = packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0
    )

    val icon: Drawable by lazy {
        packageInfo.applicationInfo.loadIcon(packageManager)
    }

    val launchIntent: Intent?
        get() = packageManager.getLaunchIntentForPackage(packageName)

}