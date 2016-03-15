@file:Suppress("NOTHING_TO_INLINE")

package lb.mini

/**
 * Kotlin wrappers for Mini.
 * @author Leonid Bushuev
 **/


inline fun<E: Any> listOf(element: E) =  Mini.listOf(element)

inline fun<E: Any> listOf(element1: E, element2: E) { Mini.listOf(element1, element2) }

inline fun<E: Any> listOf(element1: E, element2: E, element3: E) { Mini.listOf(element1, element2, element3) }

inline fun<E: Any> listOf(vararg elements: E) { Mini.listOf(elements) }

