package lb.dmagus.model.properties


enum class DataCategory
(

    val abb:      Char,
    val sizable:  Boolean,
    val scalable: Boolean,
    val explicit: Boolean

)
{

    Bool       ('B', false, false, false),
    Numeric    ('N', true,  true , false),
    Text       ('T', true,  false, false),
    DateOrTime ('D', true,  false, false),
    Items      ('I', false, false, true );


    companion object {

        @JvmStatic
        fun import(abb: kotlin.Char): DataCategory? =
            when (abb) {
                'B','b' -> Bool
                'N','n' -> Numeric
                'T','t' -> Text
                'D','d' -> DateOrTime
                'I','i' -> Items
                else    -> null
            }

    }
}
