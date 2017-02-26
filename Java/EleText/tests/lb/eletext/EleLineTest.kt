package lb.eletext


import lb.util.koassertions.mustBe
import org.junit.jupiter.api.Test


class EleLineTest {


    @Test
    fun noOffset() {
        val line = EleLine("", 1, 0, "ABC")
        line.rawString mustBe "ABC"
        line.rawLength mustBe 3
        line.meanString mustBe "ABC"
        line.meanLength mustBe 3
    }

    @Test
    fun offset_3() {
        val line = EleLine("", 2, 3, "   WORD")
        line.rawString mustBe "   WORD"
        line.rawLength mustBe 7
        line.meanString mustBe "WORD"
        line.meanLength mustBe 4
    }

    
}