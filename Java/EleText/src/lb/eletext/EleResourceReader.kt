package lb.eletext

import lb.util.functions.addSuffix
import lb.util.functions.lastPart
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream


class EleResourceReader: EleReader {


    ////// PUBLIC STATE \\\\\\

    val classLoader: ClassLoader

    val defaultFileSuffix: String?



    ////// INITIALIZATION \\\\\\

    /**
     * Creates an Elegant File reader.
     * @param capacity the capacity of the queue.
     */
    constructor(classLoader: ClassLoader? = null, defaultFileSuffix: String? = ".et", capacity: Int = 60)
        : super(capacity)
    {
        this.classLoader = classLoader ?: ClassLoader.getSystemClassLoader()
        this.defaultFileSuffix = defaultFileSuffix
    }



    ////// PUBLIC METHODS \\\\\\

    fun readResource(clazz: Class<*>, name: String) {
        val packageName = clazz.`package`.name.replace('.','/')
        val fileName =
            if (defaultFileSuffix != null) name.addSuffix(defaultFileSuffix) {!it.lastPart('/').contains('.')}
            else name
        val filePath = packageName + '/' + fileName
        val uri: URL = classLoader.getResource(filePath)
        val path = Paths.get(uri.toURI())
        val stream: Stream<String> = Files.lines(path)
        try {
            read(stream.iterator(), fileName)
        }
        finally {
            stream.close()
        }
    }

}