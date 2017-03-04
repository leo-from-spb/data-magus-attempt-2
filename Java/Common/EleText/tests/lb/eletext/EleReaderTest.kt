package lb.eletext


import lb.util.functions.EOT
import lb.util.koassertions.*
import org.junit.jupiter.api.Test
import java.util.*


/**
 * Elegant Text abstract processor.
 *
 * @author Leonid Bushuev
 */
class EleReaderTest {

    @Test
    fun offsets() {
        val text = listOf(
            "A",
            " B",
            "\tC",
            "\t D",
            "\t\tE"
        )

        val reader = EleReader()

        reader.read(text, "")

        reader.getErrors() must BeEmpty

        val result = ArrayList<EleLine>()
        reader.queue.drainTo(result)

        result[0].offset mustBe 0
        result[1].offset mustBe 1
        result[2].offset mustBe 8
        result[3].offset mustBe 9
        result[4].offset mustBe 16
    }

    @Test
    fun skipEmptyLines() {
        val text = listOf(
            "A",
            "",
            "\t",
            "\tB",
            "\t ",
            "\t\tC"
        )

        val reader = EleReader()

        reader.read(text, "")

        reader.getErrors() must BeEmpty

        val result = ArrayList<EleLine>()
        reader.queue.drainTo(result)

        result[0].meanString mustBe "A"
        result[1].meanString mustBe "B"
        result[2].meanString mustBe "C"
    }


    @Test
    fun finishWithEOT() {
        val text = listOf(
            "A",
            "",
            "\t",
            "\tB",
            "\t ",
            "\t\tC"
        )

        val reader = EleReader()
        reader.read(text, "")
        reader.getErrors() must BeEmpty

        val result = ArrayList<EleLine>()
        reader.queue.drainTo(result)
        val last = result.last()

        last.rawString  mustBeRef  EOT
        last.offset     mustBe     0
        last.eot        mustBe     true
    }

}