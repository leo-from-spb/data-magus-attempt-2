package lb.eletext


import lb.util.koassertions.*
import org.junit.jupiter.api.Test
import java.util.*


class EleResourceReaderTest {

    @Test
    fun readSimpleFile() {
        val reader = EleResourceReader()
        reader.readResource(EleResourceReaderTest::class.java, "SimpleFile1")
        reader.getErrors() must BeEmpty

        val result = ArrayList<EleLine>()
        reader.queue.drainTo(result)

        result[0].fileName mustBe "SimpleFile1.et"
        result[0].eot mustBe false

        result[0].meanString mustBe "Russia"
        result[1].meanString mustBe "Lipetsk"
        result[2].meanString mustBe "Kostomuksha"
        result[3].meanString mustBe "Saint-Petersburg"
        result[4].meanString mustBe "Moscow"
        result[5].meanString mustBe "Germany"
        result[6].meanString mustBe "Munich"

        result[7].fileName mustBe result[0].fileName
        result[7].eot mustBe true
    }

}