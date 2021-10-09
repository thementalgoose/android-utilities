package tmg.utilities.utils

import android.net.Uri
import java.io.File

object FileUtils {

    @JvmStatic
    fun deleteFile(uri: Uri): Boolean {
        uri.path?.let {
            return File(it).delete()
        }
        return false
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @JvmStatic
    fun renameFile(from: Uri?, to: Uri?): Boolean {
        if (from?.path != null && to?.path != null) {
            return File(from.path).renameTo(File(to.path))
        }
        return false
    }
}