package tmg.utilities.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import tmg.utilities.extensions.managerClipboard

class ClipboardUtils {
    companion object {
        @JvmStatic
        fun copyToClipboard(context: Context, text: String, label: String = "Copy") {
            val clipManager: ClipboardManager = context.managerClipboard
            val clipData: ClipData = ClipData.newPlainText(label, text)
            clipManager.setPrimaryClip(clipData)
        }
    }
}