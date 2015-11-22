package lb.dmagus.collections

import org.junit.Test

import org.assertj.core.api.Assertions.assertThat


class ArrayCloneListTest
{
    @Test
    fun add_basic()
    {
        val a = ArrayCloneList(Long::class)

        assertThat(a.size).isEqualTo(0)

        a.add(111L)

        assertThat(a.size).isEqualTo(1)
        assertThat(a[0]).isEqualTo(111L)

        a.add(222L)

        assertThat(a.size).isEqualTo(2)
        assertThat(a[0]).isEqualTo(111L)
        assertThat(a[1]).isEqualTo(222L)

        a.add(333L)

        assertThat(a.size).isEqualTo(3)
        assertThat(a[0]).isEqualTo(111L)
        assertThat(a[1]).isEqualTo(222L)
        assertThat(a[2]).isEqualTo(333L)
    }

}