package lb.dmagus.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ConstListTest
{

    @Test
    fun basic_1()
    {
        val list = ConstList("Aaa")

        assertThat(list.size).isEqualTo(1)
        assertThat(list.isEmpty()).isFalse()
    }

    @Test
    fun basic_2()
    {
        val list = ConstList("Aaa", "Bbb")

        assertThat(list.size).isEqualTo(2)
        assertThat(list.isEmpty()).isFalse()
    }

    @Test
    fun basic_empty()
    {
        val list = ConstList<String>()

        assertThat(list.size).isEqualTo(0)
        assertThat(list.isEmpty()).isTrue()
    }


    @Test
    fun iterate_basic()
    {
        val list = ConstList(111L, 222L, 333L)
        val it = list.iterator()
        assertThat(it.hasNext()).isTrue()
        assertThat(it.next()).isEqualTo(111L)
        assertThat(it.next()).isEqualTo(222L)
        assertThat(it.next()).isEqualTo(333L)
        assertThat(it.hasNext()).isFalse()
    }

}