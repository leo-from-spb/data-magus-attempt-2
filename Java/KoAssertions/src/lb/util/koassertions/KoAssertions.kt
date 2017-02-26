@file:Suppress("unused_parameter")

package lb.util.koassertions


import org.junit.jupiter.api.Assertions.*


infix fun <T> T?.mustBe(expected: T): T {
    assertNotNull(this)
    this!!
    assertEquals(expected, this)
    return this
}

infix fun <E> Array<E>?.mustBe(expected: Array<E>): Array<E> {
    assertNotNull(this)
    this!!
    assertArrayEquals(expected, this)
    return this
}


infix inline fun <reified E> Collection<E>?.mustBe(expected: Array<E>): Collection<E> {
    assertArrayEquals(expected, this?.toTypedArray())
    return this!!
}


infix fun <O> O?.mustBeRef(expected: O): O {
    assertNotNull(this)
    this!!
    assertSame(expected, this)
    return this
}


infix fun <S:CharSequence> S?.mustBeLike(expected: String): S {
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
object BeEmpty
object BeNotEmpty

infix fun <T> T?.must(`_`: BeNull) {
    assertNull(this)
}

infix fun <T> T?.must(`_`: BeNotNull): T {
    assertNotNull(this)
    return this!!
}

infix fun <E> Array<E>?.must(`_`: BeEmpty): Array<E>? {
    if (this != null && this.isNotEmpty()) {
        fail("Expected an empty array but it is not")
    }
    return this
}

infix fun <E> Array<E>?.must(`_`: BeNotEmpty): Array<E> {
    assertNotNull(this)
    this!!
    assertTrue(this.isNotEmpty())
    return this
}

infix fun <E:Collection<*>> E?.must(`_`: BeEmpty): E? {
    if (this != null && this.isNotEmpty()) {
        fail("Expected an empty collection but it is not")
    }
    return this
}

infix fun <E:Collection<*>> E?.must(`_`: BeNotEmpty): E {
    assertNotNull(this)
    this!!
    assertTrue(this.isNotEmpty())
    return this
}



