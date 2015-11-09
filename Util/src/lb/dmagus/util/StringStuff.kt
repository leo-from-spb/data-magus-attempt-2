@file:JvmName("StringStuff")
package lb.dmagus.util



@Suppress("NOTHING_TO_INLINE")
public inline operator fun String.get(range: IntRange): String {
    return this.substring(range.start, range.end)
}


public val String.plural: String
    get() {
        val n = this.length
        val c = this[n-1]

        return when (c) {
            's'  -> this + "es"
            'x'  -> if (n > 2 && this[n-2] == 'e') this[0..n-2]+"ices" else this + "es"
            'y'  -> this[0..n-1] + "ies"
            else -> this + 's'
        }
    }


