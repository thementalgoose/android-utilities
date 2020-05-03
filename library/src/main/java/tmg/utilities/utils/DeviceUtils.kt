package tmg.utilities.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.util.Log
import tmg.utilities.enums.DeviceRingerMode
import tmg.utilities.extensions.managerAudio
import tmg.utilities.extensions.managerTelephony
import tmg.utilities.models.DeviceStatus

class DeviceUtils {
    companion object {
        /**
         * Get device summary info
         */
        @JvmStatic
        fun getDeviceInfo(activity: Activity): DeviceStatus {
            return DeviceStatus(activity)
        }

        /**
         * Get device ringer mode
         */
        @JvmStatic
        fun getDeviceRingerMode(context: Context): DeviceRingerMode {
            return when (context.managerAudio.ringerMode) {
                AudioManager.RINGER_MODE_SILENT -> DeviceRingerMode.SILENT
                AudioManager.RINGER_MODE_VIBRATE -> DeviceRingerMode.VIBRATE
                else -> DeviceRingerMode.NORMAL
            }
        }

        /**
         * Get the device IMEI
         */
        @SuppressLint("MissingPermission")
        @JvmStatic
        fun getIMEI(activity: Activity): String {
            if (!PermissionUtils.isPermissionGranted(activity, Manifest.permission.READ_PHONE_STATE)) {
                Log.e("TMG-AndroidUtils","Permission is not granted for the IMEI - Please grant '${Manifest.permission.READ_PHONE_STATE}' permission")
            }
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity.managerTelephony.imei
            } else {
                activity.managerTelephony.deviceId
            }
        }
    }
}