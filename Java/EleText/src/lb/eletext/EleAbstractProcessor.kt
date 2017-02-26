package lb.eletext

import lb.util.functions.expandTabs
import lb.util.functions.offset
import java.util.*

abstract class EleAbstractProcessor {


    ////// INTERNAL STATE \\\\\\

    private var file: String = ""

    private var lineNr: Int = 0

    private val errors: MutableList<EleError> = ArrayList()


    ////// CONTROLLING METHODS \\\\\\

    fun reset(file: String = "", dropErrors: Boolean = false) {
        this.file = file
        this.lineNr = 0
        if (dropErrors) errors.clear()
    }

    fun process() {
        doProcessingLoop()
    }

    fun getErrors(): Array<EleError> = errors.toTypedArray()



    ////// METHODS TO OVERRIDE \\\\\\

    abstract fun takeNextLine(): String?

    open fun removeComments(string: String): String = string

    abstract fun processLine(line: EleLine)


    
    ////// IMPLEMENTATION \\\\\\

    private fun doProcessingLoop() {
        do {

            lineNr++
            val rawString = takeNextLine() ?: break
            processOneRawStringSafe(rawString)

        } while (true)
    }


    private fun processOneRawStringSafe(string: String) {
        try {
            processOneRawString(string)
        }
        catch (e: Exception) {
            registerException(e)
        }
    }

    private fun processOneRawString(string: String) {
        var str = string.trimEnd()
        if (str.length == 0) return
        str = removeComments(str).trimEnd()
        if (str.length == 0) return

        str = str.expandTabs()
        val offset = str.offset()
        if (offset < 0) return

        val line = EleLine(file, lineNr, offset, str)
        processLine(line)
    }

    private fun registerException(e: Exception) {
        val message = (e.message ?: "Unknown error") + " (${e.javaClass.simpleName})"
        val ee = EleError(file, lineNr, message = message)
        registerError(ee)
    }

    private fun registerError(error: EleError) {
        errors.add(error)
    }

}