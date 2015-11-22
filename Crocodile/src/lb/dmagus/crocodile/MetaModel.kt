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
    val refs = ArrayList<MetaRef>()
    val properties = ArrayList<MetaProperty>()

    val top: Boolean get() = "TopSpace" in interfaces
    val leaf: Boolean get() = families.isEmpty()
    val hasFamilies: Boolean get() = families.isNotEmpty()
    val hasRefs: Boolean get() = refs.isNotEmpty()
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


data class MetaRef
(
    var refName:     String,
    var targetClass: String,
    var far:         Boolean,
    var multi:       Boolean,
    var ordered:     Boolean
)
{
    val refNames:    String get() = refName.plural
    val refNameCap:  String get() = refName.capitalize()
    val refNamesCap: String get() = refName.plural.capitalize()
}



data class MetaProperty
(
    var name:    String,
    var type:    String,
    var default: String
)