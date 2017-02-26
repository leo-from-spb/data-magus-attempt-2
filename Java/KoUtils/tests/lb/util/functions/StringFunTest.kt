package lb.util.functions

import lb.util.koassertions.*
import org.junit.jupiter.api.Test

class StringFunTest {

    @Test
    fun expandTabs_basic() {
        // ﹇
        "no tabs"     .expandTabs()  mustBe  "no tabs"
        "\t"          .expandTabs()  mustBe  "        "
        "\t\t"        .expandTabs()  mustBe  "                "
        "123\t9"      .expandTabs()  mustBe  "123     9"
        "123\t9\txyz" .expandTabs()  mustBe  "123     9       xyz"
        // ﹈
    }

    @Test
    fun expandTabs_saveRef() {
        val s = "no tabs"
        s.expandTabs()  mustBeRef  s
    }


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