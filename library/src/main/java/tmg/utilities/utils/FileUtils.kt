package tmg.utilities.utils

import android.net.Uri
import java.io.File

class FileUtils {
    companion object {

        fun deleteFile(uri: Uri) {
            uri.path?.let {
                File(it).delete()
            }
        }

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        fun renameFile(from: Uri, to: Uri) {
            if (from.path != null && to.path != null) {
                File(from.path).renameTo(File(to.path))
            }
        }
    }
}