@file:JvmName("NumberFun")
@file:Suppress("nothing_to_inline")


package lb.util.functions


infix fun Int.forceToRange(range: IntRange): Int =
    when {
        this < range.start -> range.start
        this > range.endInclusive -> range.endInclusive
        else -> this
    }


infix fun Long.forceToRange(range: LongRange): Long =
    when {
        this < range.start -> range.start
        this > range.endInclusive -> range.endInclusive
        else -> this
    }


