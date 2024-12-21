package tmg.utilities.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import tmg.utilities.models.PermissionRequestResult

class PermissionUtils {
    companion object {
        /**
         * Check if a single permission is granted or not
         * @param activity
         * @param permission
         */
        @JvmStatic
        fun isPermissionsGranted(activity: Activity, vararg permission: String): Map<String, Boolean> {
            val result = getPermissionsState(activity, *permission)
            return buildMap {
                putAll(result.granted.map { it to true })
                putAll(result.showRational.map { it to true })
                putAll(result.denied.map { it to true })
            }
        }

        @JvmStatic
        fun isPermissionGranted(context: Context, permission: String): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            } else {
                context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            }
        }

        /**
         * Check if a list of permissions is granted
         * @param activity
         * @param permissions
         */
        @JvmStatic
        fun getPermissionsState(activity: Activity, vararg permissions: String): PermissionRequestResult {
            val granted: MutableList<String> = mutableListOf()
            val showRational: MutableList<String> = mutableListOf()
            val denied: MutableList<String> = mutableListOf()
            for (permission in permissions) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val result = activity.checkSelfPermission(permission)
                    if (result == PackageManager.PERMISSION_DENIED) {
                        if (activity.shouldShowRequestPermissionRationale(permission)) {
                            // Permission was denied by the user
                            showRational.add(permission)
                        }
                        else {
                            denied.add(permission)
                        }
                    }
                    else {
                        granted.add(permission)
                    }
                }
                else {
                    if (activity.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                        granted.add(permission)
                    }
                    else {
                        Log.e("PermissionUtils", "Permission has been denied but the device is pre runtime permissions - Is '$permission' declared inside the Manifest?")
                        denied.add(permission)
                    }
                }
            }
            return PermissionRequestResult(granted, showRational, denied)
        }
    }
}