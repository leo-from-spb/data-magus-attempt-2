@file:JvmName("StringFun")

package lb.util.functions


infix fun String?.splitToPairBy(delimiter: Char): Pair<String?,String?> {
    if (this == null || this.isEmpty()) return pairOfStringNulls
    val d = this.indexOf(delimiter)
    return if (d >= 0) Pair(this.substring(0,d), this.substring(d+1)) else Pair(this,"")
}


val pairOfStringNulls: Pair<String?,String?> = Pair<String?,String?>(null,null)