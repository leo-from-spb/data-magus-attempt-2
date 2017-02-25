package lb.util.functions

import lb.util.koassertions.mustBe
import org.junit.jupiter.api.Test

class StringFunTest {

    @Test
    fun splitToPairBy_basic() {
        // ﹇
        "AAA:BBB" splitToPairBy ':' mustBe Pair("AAA","BBB")
        "AAA"     splitToPairBy ':' mustBe Pair("AAA",null)
        "AAA:"    splitToPairBy ':' mustBe Pair("AAA",null)
        ":BBB"    splitToPairBy ':' mustBe Pair(null,"BBB")
        ""        splitToPairBy ':' mustBe Pair(null,null)
        null      splitToPairBy ':' mustBe Pair(null,null)
        // ﹈
    }


}