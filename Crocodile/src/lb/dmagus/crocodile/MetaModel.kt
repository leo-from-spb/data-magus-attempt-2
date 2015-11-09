package lb.dmagus.crocodile

import java.util.*

/**
 * @author Leonid Bushuev from JetBrains
 **/
class MetaModel
{

    val files   = ArrayList<MetaFile>()
    val classes = TreeMap<String,MetaFile>(String.CASE_INSENSITIVE_ORDER)

}


data class MetaFile
(
    var pack:        String = "none"    ,
    var open:        String = "open"    ,
    var name:        String = "object"  ,
    var klass:       String = "Object"  ,
    var base:        String = "Element" ,
    var parentName:  String = "element" ,
    var parentClass: String = "Element"
)
{
    val interfaces = ArrayList<String>()
    val families   = ArrayList<String>()
    val properties = ArrayList<MetaProperty>()
}


data class MetaProperty
(
    var name:    String,
    var type:    String,
    var default: String
)