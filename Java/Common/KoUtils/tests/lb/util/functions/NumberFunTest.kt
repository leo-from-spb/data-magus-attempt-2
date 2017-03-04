package lb.util.functions

import lb.util.koassertions.mustBe
import org.junit.jupiter.api.Test

class NumberFunTest {

    @Test
    fun forceToRange_int() {
        25 forceToRange 15..30 mustBe 25
        10 forceToRange 15..30 mustBe 15
        90 forceToRange 15..30 mustBe 30
    }

}