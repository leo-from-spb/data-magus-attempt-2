package lb.dmag.util

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter


infix fun File.writeText(text: String)
{
    if (!this.parentFile.isDirectory)
        throw IOException("""Cannot write to file "${this.absolutePath}": no directory""")

    val fos = FileOutputStream(this)
    try {
        val fwr = OutputStreamWriter(fos, Charsets.UTF_8)
        try {
            fwr.write(text)
        }
        finally {
            fwr.close()
        }
    }
    finally {
        fos.close()
    }
}
