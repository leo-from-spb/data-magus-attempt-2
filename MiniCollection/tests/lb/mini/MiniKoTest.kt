package lb.mini

import org.junit.Test


class MiniKoTest
{

    @Test
    fun list_basic_1()
    {
        val list: List<String> = listOf("AAA");
        assert(list.size == 1)
        assert(list.isEmpty() == false)
        assert(list.isNotEmpty() == true)
        assert(list.contains("AAA"))
    }



}