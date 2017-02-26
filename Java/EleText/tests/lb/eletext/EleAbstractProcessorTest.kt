package lb.eletext


import lb.util.koassertions.*
import org.junit.jupiter.api.Test
import java.util.*


/**
 * Elegant Text abstract processor.
 *
 * @author Leonid Bushuev
 */
class EleAbstractProcessorTest {

    @Test
    fun offsets() {
        val iter = arrayOf(
            "A",
            " B",
            "\tC",
            "\t D",
            "\t\tE"
        ).iterator()

        val result = ArrayList<Int>()

        val processor = object: EleAbstractProcessor() {
            override fun takeNextLine() = if (iter.hasNext()) iter.next() else null
            override fun processLine(line: EleLine) { result.add(line.offset) }
        }

        processor.process()

        processor.getErrors() must BeEmpty

        result[0] mustBe 0
        result[1] mustBe 1
        result[2] mustBe 8
        result[3] mustBe 9
        result[4] mustBe 16
    }

    @Test
    fun skipEmptyLines() {
        val iter = arrayOf(
            "A",
            "",
            "\t",
            "\tB",
            "\t ",
            "\t\tC"
        ).iterator()

        val result = ArrayList<String>()

        val processor = object: EleAbstractProcessor() {
            override fun takeNextLine() = if (iter.hasNext()) iter.next() else null
            override fun processLine(line: EleLine) { result.add(line.meanString) }
        }

        processor.process()

        processor.getErrors() must BeEmpty

        result mustBe arrayOf("A","B","C")
    }

}