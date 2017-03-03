package lb.eletext

import lb.util.functions.*
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

open class EleReader {


    ////// PUBLIC STATE \\\\\\

    val queue: BlockingQueue<EleLine>


    ////// INTERNAL STATE \\\\\\


    private val errors: MutableList<EleError> = ArrayList()


    ////// INITIALIZATION \\\\\\

    /**
     * Creates an Elegant File reader.
     * @param capacity the capacity of the queue.
     */
    constructor(capacity: Int = 60): super() {
        queue = ArrayBlockingQueue<EleLine>(capacity)
    }


    ////// PUBLIC METHODS \\\\\\

    fun read(iterable: Iterable<String>, fileName: String) {
        read(iterable.iterator(), fileName)
    }

    fun read(iterator: Iterator<String>, fileName: String) {
        var lineNr = 0
        try {
            do {
                lineNr++
                val str: String
                if (iterator.hasNext()) {
                    str = iterator.next()
                    if (str === EOT) break
                    processOneString(fileName, lineNr, str)
                }
                else {
                    break
                }
            }
            while (true)
        }
        finally {
            processLine(EleLine(fileName, lineNr, 0, EOT))
        }
    }

    fun getErrors(): Array<EleError> = errors.toTypedArray()



    ////// METHODS TO OVERRIDE \\\\\\

    open fun removeComments(string: String): String = string

    
    ////// IMPLEMENTATION \\\\\\

    private fun processOneStringSafe(fileName: String, lineNr: Int, string: String) {
        try {
            processOneString(fileName, lineNr, string)
        }
        catch (e: Throwable) {
            registerException(fileName, lineNr, e)
        }
    }

    private fun processOneString(fileName: String, lineNr: Int, string: String) {
        var str = string.trimEnd()
        if (str.length == 0) return
        str = removeComments(str).trimEnd()
        if (str.length == 0) return

        str = str.expandTabs()
        val offset = str.offset()
        if (offset < 0) return

        val line = EleLine(fileName, lineNr, offset, str)
        processLine(line)
    }


    fun processLine(line: EleLine) {
        queue.put(line)
    }


    private fun registerException(fileName: String, lineNr: Int, exception: Throwable) {
        val message = (exception.message ?: "Unknown error")+" (${exception.javaClass.simpleName})"
        val ee = EleError(fileName, lineNr, message = message)
        registerError(ee)
    }

    private fun registerError(error: EleError) {
        errors.add(error)
    }

}