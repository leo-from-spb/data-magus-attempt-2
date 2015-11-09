package lb.dmagus.crocodile

import kotlin.text.Regex
import kotlin.text.RegexOption


fun regex(r: String): Regex
{
    val re = r.replace("  ", "\\s+").replace(" ", "\\s*")
    return Regex(re, RegexOption.IGNORE_CASE)
}


infix fun CharSequence.match(regex: Regex) = regex.matchEntire(this)



fun class2name(s: String) = s[0].toLowerCase().toString() + s.subSequence(1, s.length)


infix fun StringBuilder.replace(pair: Pair<String,String>)
{
    val str1 = pair.first
    val str2 = pair.second
    val len1 = str1.length
    val len2 = str2.length

    var pos = this.indexOf(str1)
    while (pos >= 0)
    {
        this.replace(pos, pos+len1, str2)
        pos = this.indexOf(str1, pos+len2)
    }
}

infix fun StringBuilder.remove(marks: Pair<String,String>)
{
    val str1 = marks.first
    val str2 = marks.second
    val len1 = str1.length
    val len2 = str2.length

    var pos1 = this.indexOf(str1)
    while (pos1 >= 0)
    {
        val pos2 = this.indexOf(str2, pos1+len1)
        if (pos2 > 0)
        {
            this.delete(pos1, pos2+len2)
            pos1 = this.indexOf(str1, pos1)
        }
        else
        {
            pos1 = -1
        }
    }
}



fun say(phrase: String) { System.out.println(phrase) }
