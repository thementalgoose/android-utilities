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
        @SuppressLint("MissingPermission")
        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            if (!PermissionUtils.isPermissionGranted(context, Manifest.permission.ACCESS_NETWORK_STATE)) {
                Log.e("NetworkUtils", "Permission '${Manifest.permission.ACCESS_NETWORK_STATE}' is not granted")
            }
            val activeNetworkInfo = context.managerConnectivity?.activeNetworkInfo
            return activeNetworkInfo?.isConnected == true
        }

        /**
         * Get the WiFi MAC address
         * Null if it cannot be found
         */
        @SuppressLint("HardwareIds")
        @JvmStatic
        fun getWifiMacAddress(context: Context): String? {
            if (!PermissionUtils.isPermissionGranted(context, Manifest.permission.ACCESS_WIFI_STATE)) {
                Log.e("NetworkUtils", "Permission '${Manifest.permission.ACCESS_WIFI_STATE}' is not granted")
                return null
            }
            return context.managerWifi?.connectionInfo?.macAddress
        }

        /**
         * Get the connection type of the device
         */
        @SuppressLint("MissingPermission")
        @JvmStatic
        fun getNetworkState(context: Context): NetworkConnectionType {
            context.managerConnectivity?.run {
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
        fun isWifiEnabled(context: Context): Boolean? {
            if (!PermissionUtils.isPermissionGranted(context, Manifest.permission.ACCESS_WIFI_STATE)) {
                Log.e("NetworkUtils", "Permission '${Manifest.permission.ACCESS_WIFI_STATE}' is not granted")
                return null
            }
            return context.managerWifi?.isWifiEnabled
        }

        /**
         * Get if the device is 2G, 3G, or 4G
         * Note: 5G Not supported yet
         */
        @SuppressLint("MissingPermission")
        @JvmStatic
        fun getNetworkDataState(context: Context): NetworkDataState {
            if (!PermissionUtils.isPermissionGranted(context, Manifest.permission.READ_PHONE_STATE)) {
                Log.e("NetworkUtils", "Permission '${Manifest.permission.READ_PHONE_STATE}' is not granted")
            }
            return when (context.managerTelephony?.networkType) {
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