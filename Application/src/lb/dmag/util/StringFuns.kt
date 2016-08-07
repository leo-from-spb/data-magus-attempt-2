package lb.dmag.util


infix fun String.divide(d: Char): Couple<String>
{
    val n = this.length
    if (n == 0) return EmptyStringCouple
    val p = this.indexOf(d)
    if (p < 0) return Couple(this,"")
    return Couple(this.substring(0,p), this.substring(p+1,n))
}

infix fun String.divideTrim(d: Char): Couple<String>
{
    val n = this.length
    if (n == 0) return EmptyStringCouple
    val p = this.indexOf(d)
    if (p < 0) return Couple(this.trim(),"")
    return Couple(this.substring(0,p).trim(), this.substring(p+1,n).trim())
}



infix fun String.divide(d: String): Couple<String>
{
    val n = this.length
    if (n == 0) return EmptyStringCouple
    val m = d.length
    if (m == 0) throw IllegalArgumentException("Attempt to split a string by an empty delimiter")
    if (m > n) return Couple(this,"")
    val p = this.indexOf(d)
    if (p < 0) return Couple(this,"")
    return Couple(this.substring(0,p), this.substring(p+m,n))
}

infix fun String.divideTrim(d: String): Couple<String>
{
    val n = this.length
    if (n == 0) return EmptyStringCouple
    val m = d.length
    if (m == 0) throw IllegalArgumentException("Attempt to split a string by an empty delimiter")
    if (m > n) return Couple(this.trim(),"")
    val p = this.indexOf(d)
    if (p < 0) return Couple(this.trim(),"")
    return Couple(this.substring(0,p).trim(), this.substring(p+m,n).trim())
}



val EmptyStringCouple = Couple("", "")


fun MutableMap<String,String>.assign(key: String, value: String?)
{
    if (value != null) {
        this.put(key, value)
    }
    else {
        this.remove(key)
    }
}