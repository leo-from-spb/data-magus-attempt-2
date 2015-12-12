package lb.dmagus.collections

import org.junit.Test

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.assertTrue


class ArrayCloneListTest
{
    @Test
    fun add_String()
    {
        val a = ArrayCloneList(String::class)

        assertThat(a.size).isEqualTo(0)

        a.add("AAA")

        assertThat(a.size).isEqualTo(1)
        assertThat(a[0]).isEqualTo("AAA")

        a.add("BBB")

        assertThat(a.size).isEqualTo(2)
        assertThat(a[0]).isEqualTo("AAA")
        assertThat(a[1]).isEqualTo("BBB")

        a.add("CCC")

        assertThat(a.size).isEqualTo(3)
        assertThat(a[0]).isEqualTo("AAA")
        assertThat(a[1]).isEqualTo("BBB")
        assertThat(a[2]).isEqualTo("CCC")
    }

    @Test
    fun add_Int()
    {
        val a = ArrayCloneList(Int::class)

        assertThat(a.size).isEqualTo(0)

        a.add(111)

        assertThat(a.size).isEqualTo(1)
        assertThat(a[0]).isEqualTo(111)

        a.add(222)

        assertThat(a.size).isEqualTo(2)
        assertThat(a[0]).isEqualTo(111)
        assertThat(a[1]).isEqualTo(222)

        a.add(333)

        assertThat(a.size).isEqualTo(3)
        assertThat(a[0]).isEqualTo(111)
        assertThat(a[1]).isEqualTo(222)
        assertThat(a[2]).isEqualTo(333)
    }
    @Test
    fun add_Long()
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

    @Test
    fun add_Number()
    {
        val x: Byte = 123;
        val y: Short = 456;
        val z: Long = 789L;

        val a = ArrayCloneList(Number::class)

        a.add(x)
        a.add(y)
        a.add(z)

        /*
        assertThat(a[0]).isSameAs(x)
        assertThat(a[1]).isSameAs(y)
        assertThat(a[2]).isSameAs(z)
        */

        assertTrue(a[0] == x)
        assertTrue(a[1] == y)
        assertTrue(a[2] == z)
    }

}