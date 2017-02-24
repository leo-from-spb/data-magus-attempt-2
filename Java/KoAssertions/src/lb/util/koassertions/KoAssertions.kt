@file:Suppress("unused_parameter")

package lb.util.koassertions


import org.junit.jupiter.api.Assertions.*


infix fun <S> S?.mustBe(expected: S): S {
    assertNotNull(this)
    this!!
    assertEquals(expected, this)
    return this
}


infix fun <S: CharSequence> S?.mustBeLike(expected: String): S {
    assertNotNull(this)
    this!!
    val s = this.toString()
    if (String.CASE_INSENSITIVE_ORDER.compare(s, expected) == 0) {
        return this
    }
    else {
        assertEquals(expected, s)
        return this // only for compiler, the previous function must fail
    }
}


infix fun <T> Collection<T>?.mustHasSize(size: Int): Collection<T> {
    assertNotNull(this)
    this!!
    val n = this.size
    assertEquals(size, n)
    return this
}

infix fun <T> Array<T>?.mustHasSize(size: Int): Array<T> {
    assertNotNull(this)
    this!!
    val n = this.size
    assertEquals(size, n)
    return this
}


object BeNull
object BeNotNull
object BeNotEmpty

infix fun <S> S?.must(`_`: BeNull) {
    assertNull(this)
}

infix fun <S> S?.must(`_`: BeNotNull): S {
    assertNotNull(this)
    return this!!
}

infix fun <S:Collection<*>> S?.must(`_`: BeNotEmpty): S {
    assertNotNull(this)
    this!!
    assertTrue(this.isNotEmpty())
    return this
}



