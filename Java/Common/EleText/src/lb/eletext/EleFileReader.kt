package lb.eletext

import java.nio.file.*
import java.util.stream.Stream


class EleFileReader: EleReader {


    ////// PUBLIC STATE \\\\\\

    var basePath: Path

    

    ////// INITIALIZATION \\\\\\

    /**
     * Creates an Elegant File reader.
     * @param capacity the capacity of the queue.
     */
    constructor(basePath: Path? = null, capacity: Int = 60)
        : super(capacity)
    {
        this.basePath = basePath ?: Paths.get(".")
    }



    ////// PUBLIC METHODS \\\\\\

    fun readFile(fileName: String) {
        val absolute = fileName.startsWith('/')
        val path = if (absolute) Paths.get(fileName) else basePath.resolve(fileName)
        val stream: Stream<String> = Files.lines(path)
        try {
            read(stream.iterator(), fileName)
        }
        finally {
            stream.close()
        }
    }


}