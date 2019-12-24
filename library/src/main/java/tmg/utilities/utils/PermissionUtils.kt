package tmg.utilities.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import tmg.utilities.models.PermissionRequestResult
import tmg.utilities.models.PermissionRequestState

class PermissionUtils {
    companion object {
        /**
         * Check if a single permission is granted or not
         * @param activity
         * @param permission
         */
        @JvmStatic
        fun isPermissionGranted(activity: Activity, permission: String): Boolean {
            return getPermissionsState(activity, permission).isAllGranted
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
                        Log.e("TMG-AndroidUtils", "Permission has been denied but but the device is pre runtime permissions - Is '$permission' declared inside the Manifest?")
                        denied.add(permission)
                    }
                }
            }
            return PermissionRequestResult(granted, showRational, denied)
        }
    }
}