package tmg.utilities.models

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Build

data class InstalledPackageModel(
    private val packageManager: PackageManager,
    val packageInfo: ResolveInfo,
    val appName: String,
    val packageName: String,
    val iconRes: Int
) {
    constructor(
        packageManager: PackageManager,
        packageInfo: ResolveInfo
    ) : this(
        packageManager = packageManager,
        packageInfo = packageInfo,
        appName = packageInfo.loadLabel(packageManager).toString(),
        packageName = packageInfo.resolvePackageName,
        iconRes = packageInfo.icon
    )

    val icon: Drawable by lazy {
        packageInfo.loadIcon(packageManager)
    }

    val launchIntent: Intent?
        get() = packageManager.getLaunchIntentForPackage(packageName)

}