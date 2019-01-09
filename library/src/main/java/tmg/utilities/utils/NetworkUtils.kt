package tmg.utilities.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils {
    companion object {
        @SuppressLint("MissingPermission")
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}