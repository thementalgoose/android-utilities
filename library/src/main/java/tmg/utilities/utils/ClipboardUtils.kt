package tmg.utilities.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import tmg.utilities.extensions.managerClipboard

class ClipboardUtils {
    companion object {
        /**
         * @param context
         * @param text The text to be copied
         * @param label An associated label for the text being copied, defaults to ""
         * @param copySuccessfulToastMessage For API 33 and above, clipboard manager displays an overlay. This fallback is for API 32 and below to display toast message
         */
        @JvmStatic
        fun copyToClipboard(context: Context, text: String, label: String = "", copySuccessfulToastMessage: String?) {
            val clipManager: ClipboardManager? = context.managerClipboard
            val clipData: ClipData = ClipData.newPlainText(label, text)
            clipManager?.setPrimaryClip(clipData)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2 && copySuccessfulToastMessage != null) {
                Toast.makeText(context, copySuccessfulToastMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}