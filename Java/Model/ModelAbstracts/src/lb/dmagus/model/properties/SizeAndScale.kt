package lb.dmagus.model.properties


data class SizeAndScale
(
    val size:     Short     = noSize,
    val scale:    Short     = noScale,
    val unit:     SizeUnit? = null,
    val explicit: Boolean   = false
)
{
    fun export() {
        val b = StringBuilder(8)
        when (size) {
            noSize   -> {}
            starSize -> b.append('*')
            else     -> b.append(size)
        }
        if (scale != noScale) {
            b.append(',').append(scale)
        }
        if (unit != null) {
            b.append('_').append(unit.Abb)
            if (explicit) b.append('!')
        }
    }



    companion object {
        
        const val noSize:   Short = -1
        const val starSize: Short = Short.MAX_VALUE
        const val noScale:  Short = 0

    }

}

