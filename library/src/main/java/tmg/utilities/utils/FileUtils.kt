package tmg.utilities.utils

import android.net.Uri
import java.io.File
import java.nio.file.Files.delete

class FileUtils {
    companion object {

        fun deleteFile(uri: Uri) {
            uri.path?.let {
                File(it).delete()
            }
        }

        fun renameFile(from: Uri, to: Uri) {
            from.path?.let {
                File(it).renameTo(File(it))
            }
        }
    }
}