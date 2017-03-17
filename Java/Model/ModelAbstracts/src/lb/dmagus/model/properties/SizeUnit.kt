package lb.dmagus.model.properties

/**
 * Data type size unit.
 */
enum class SizeUnit  
(
    val Abb: kotlin.Char
)
{
    Byte  ('B'),
    Char  ('C'),
    Digit ('D');

    companion object {

        @JvmStatic
        fun import(abb: kotlin.Char): SizeUnit? =
            when (abb) {
                'B','b' -> Byte
                'C','c' -> Char
                'D','d' -> Digit
                else    -> null
            }

    }
}



