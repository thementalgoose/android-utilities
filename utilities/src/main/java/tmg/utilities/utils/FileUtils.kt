package tmg.utilities.utils

import android.net.Uri
import java.io.File
import java.nio.file.Files.delete

class FileUtils {
    companion object {

        fun deleteFile(uri: Uri) {
            File(uri.path).delete()
        }

        fun renameFile(from: Uri, to: Uri) {
            File(from.path).renameTo(File(to.path))
        }
    }
}