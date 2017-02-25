@file:JvmName("StringFun")
@file:Suppress("ReplaceSizeCheckWithIsNotEmpty")

package lb.util.functions


val String?.nullize: String?
    /*inline*/ get() = if (this != null && this.length > 0) this else null


infix fun String?.splitToPairBy(delimiter: Char): Pair<String?,String?> {
    if (this == null || this.isEmpty()) return pairOfStringNulls
    val d = this.indexOf(delimiter)
    return if (d >= 0) Pair(this.substring(0,d).nullize, this.substring(d+1).nullize) else Pair(this,null)
}


val pairOfStringNulls: Pair<String?,String?> = Pair<String?,String?>(null,null)
