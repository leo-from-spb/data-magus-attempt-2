@file:JvmName("StringFun")
@file:Suppress("ReplaceSizeCheckWithIsNotEmpty")

package lb.util.functions


/**
 * The "End of Text" marker.
 */
const val EOT = "\uFFFF"


val String?.nullize: String?
    /*inline*/ get() = if (this != null && this.length > 0) this else null


fun String?.offset(): Int {
    this ?: return -1
    val n = this.length
    for (i in 0..n-1) if (!this[i].isWhitespace()) return i
    return -1
}


fun String.expandTabs(tabSize: Int = 8): String {
    var t = this.indexOf('\t')
    if (t < 0) return this

    val n = this.length
    val b = StringBuilder(n+24)
    var p = 0
    while (t >= 0) {
        if (t > p) b.append(this, p, t)
        val m = b.length
        val x = tabSize - (m % tabSize)
        for (i in 1..x) b.append(' ')
        p = t + 1
        t = this.indexOf('\t', p)
    }

    if (p < n) b.append(this, p, n)

    return b.toString()
}


infix fun String?.splitToPairBy(delimiter: Char): Pair<String?,String?> {
    if (this == null || this.isEmpty()) return pairOfStringNulls
    val d = this.indexOf(delimiter)
    return if (d >= 0) Pair(this.substring(0,d).nullize, this.substring(d+1).nullize) else Pair(this,null)
}


val pairOfStringNulls: Pair<String?,String?> = Pair<String?,String?>(null,null)


fun String.addSuffix(suffix: String, condition: (String) -> Boolean = {!it.endsWith(suffix)}): String {
    val met = condition(this)
    return if (met) this + suffix else this
}


fun String.lastPart(delimiter: Char): String {
    val d = this.indexOf(delimiter)
    return if (d >= 0) this.substring(d+1) else  this
}