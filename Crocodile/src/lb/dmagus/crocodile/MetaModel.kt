package lb.dmagus.crocodile

import lb.dmagus.util.plural
import java.util.*

/**
 * @author Leonid Bushuev from JetBrains
 **/
class MetaModel
{

    val nodes = ArrayList<MetaNode>()
    val classes = TreeMap<String, MetaNode>(String.CASE_INSENSITIVE_ORDER)

}


data class MetaNode
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
    var parent: MetaNode? = null

    val interfaces = ArrayList<String>()
    val families   = ArrayList<MetaFamily>()
    val properties = ArrayList<MetaProperty>()

    val top: Boolean get() = "TopSpace" in interfaces
    val leaf: Boolean get() = families.isEmpty()
    val hasFamilies: Boolean get() = families.isNotEmpty()
    val hasProperties: Boolean get() = properties.isNotEmpty()
}


data class MetaFamily
(
    var klass:  String
)
{
    val name:    String get() = klass.decapitalize()
    val names:   String get() = name.plural
    val klasses: String get() = klass.plural
}




data class MetaProperty
(
    var name:    String,
    var type:    String,
    var default: String
)