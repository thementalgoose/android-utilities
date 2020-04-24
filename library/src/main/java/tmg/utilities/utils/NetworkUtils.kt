package tmg.utilities.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log
import tmg.utilities.enums.NetworkDataState
import tmg.utilities.extensions.managerConnectivity
import tmg.utilities.extensions.managerTelephony
import tmg.utilities.extensions.managerWifi
import tmg.utilities.models.NetworkConnectionType


class NetworkUtils {
    companion object {
        /**
         * Check if network is available
         */
        @JvmStatic
        @SuppressLint("MissingPermission")
        fun isNetworkAvailable(context: Context): Boolean {
            val activeNetworkInfo = context.managerConnectivity.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        /**
         * Get the WiFi MAC address
         * Null if it cannot be found
         */
        @JvmStatic
        @SuppressLint("MissingPermission", "HardwareIds")
        fun getWifiMacAddress(activity: Activity): String? {
            if (PermissionUtils.isPermissionGranted(activity, Manifest.permission.ACCESS_WIFI_STATE)) {
                Log.e("TMG-AndroidUtils", "Permission is not granted for the MAC address - Please grant '${Manifest.permission.ACCESS_WIFI_STATE}' permission")
                return null
            }
            val info = activity.managerWifi.connectionInfo
            return info.macAddress
        }

        /**
         * Get the connection type of the device
         */
        @SuppressLint("MissingPermission")
        @JvmStatic
        fun getNetworkState(context: Context): NetworkConnectionType {
            context.managerConnectivity.run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getNetworkCapabilities(activeNetwork)?.run {
                        return when {
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkConnectionType.WIFI
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkConnectionType.MOBILE
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkConnectionType.ETHERNET
                            hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> NetworkConnectionType.BLUETOOTH
                            else -> NetworkConnectionType.NOT_FOUND
                        }
                    }
                } else {
                    @Suppress("DEPRECATION")
                    activeNetworkInfo?.run {
                        return when (type) {
                            ConnectivityManager.TYPE_WIFI -> NetworkConnectionType.WIFI
                            ConnectivityManager.TYPE_MOBILE -> NetworkConnectionType.MOBILE
                            ConnectivityManager.TYPE_ETHERNET -> NetworkConnectionType.ETHERNET
                            ConnectivityManager.TYPE_BLUETOOTH -> NetworkConnectionType.BLUETOOTH
                            else -> NetworkConnectionType.NOT_FOUND
                        }
                    }
                }
            }
            return NetworkConnectionType.NOT_FOUND
        }

        /**
         * Check if WiFi is enabled
         * Null if it cannot be found
         */
        @JvmStatic
        fun isWifiEnabled(activity: Activity): Boolean? {
            if (PermissionUtils.isPermissionGranted(activity, Manifest.permission.ACCESS_WIFI_STATE)) {
                Log.e("TMG-AndroidUtils", "Permission is not granted for the WiFi state - Please grant '${Manifest.permission.ACCESS_WIFI_STATE}' permission")
                return null
            }
            return activity.managerWifi.isWifiEnabled
        }

        /**
         * Get if the device is 2G, 3G, or 4G
         * Note: 5G Not supported yet
         */
        @JvmStatic
        fun getNetworkDataState(context: Context): NetworkDataState {
            return when (context.managerTelephony.networkType) {
                TelephonyManager.NETWORK_TYPE_GPRS,
                TelephonyManager.NETWORK_TYPE_EDGE,
                TelephonyManager.NETWORK_TYPE_CDMA,
                TelephonyManager.NETWORK_TYPE_1xRTT,
                TelephonyManager.NETWORK_TYPE_IDEN -> NetworkDataState.DATA_2G
                TelephonyManager.NETWORK_TYPE_UMTS,
                TelephonyManager.NETWORK_TYPE_EVDO_0,
                TelephonyManager.NETWORK_TYPE_EVDO_A,
                TelephonyManager.NETWORK_TYPE_HSDPA,
                TelephonyManager.NETWORK_TYPE_HSUPA,
                TelephonyManager.NETWORK_TYPE_HSPA,
                TelephonyManager.NETWORK_TYPE_EVDO_B,
                TelephonyManager.NETWORK_TYPE_EHRPD,
                TelephonyManager.NETWORK_TYPE_HSPAP -> NetworkDataState.DATA_3G
                TelephonyManager.NETWORK_TYPE_LTE -> NetworkDataState.DATA_4G
                else -> NetworkDataState.NOT_FOUND
            }
        }
    }
}