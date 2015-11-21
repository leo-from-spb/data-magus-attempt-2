package lb.dmagus.model.concept

import lb.dmagus.model.core.*
import java.util.concurrent.atomic.AtomicReference


/**
 * The attribute.
 *
 * <p>
 * This class is generated by Gena the Crocodile.
 * Don't modify it manually.
 * </p>
 **/
public open class Attribute : ConceptElement 
{


    //// LIFE CYCLE STUFF \\\\

    val entity: Entity

    constructor(entity: Entity) : super(entity.model)
    {
        this.entity = entity
    }

    override val parentNode: Node
        get() = entity




    //// NO FAMILIES \\\\
    override val families = emptyList<Family<Node,Element>>()

    override val childNodes: Iterable<Node>
        get() = emptySet()



    
    //// REFERENCES \\\\
    
    
    private var domainArc: AttributeDomainArc? = null
    var domain: Domain?
        get() {
            return domainArc?.domain
        }
        set(value) {
            val oldArc = domainArc
            if (oldArc?.domain == value) return
            modifying()
            if (oldArc != null) {
                domainArc = null
                oldArc.drop()
            }
            if (value != null) domainArc = AttributeDomainArc(this,value)
        }
    



    //// PROPERTIES \\\\


    var abstract: Boolean = false
        set (value) {
            if (field == value) return
            modifying()
            field = value
        }

    var autoincrement: Boolean = false
        set (value) {
            if (field == value) return
            modifying()
            field = value
        }




}


/**
 * The attribute family.
 *
 * <p>
 * This class is generated by Gena the Crocodile.
 * Don't modify it manually.
 * </p>
 **/
public class AttributeFamily : Family<Entity, Attribute>
{

    val entity: Entity

    override val owner: Entity
        get() = entity


    private val array: AtomicReference<Array<Attribute>> = AtomicReference(emptyArray())


    constructor(entity: Entity) : super()
    {
        this.entity = entity
    }


    public fun newAttribute(): Attribute
    {
        val attribute = Attribute(entity)
        val attributeInArray = arrayOf(attribute)

        do {
            val oldArray = array.get();
            val newArray = oldArray + attributeInArray;
            val ok =
                array.compareAndSet(oldArray, newArray)
        } while (!ok)

        return attribute
    }

    override fun iterator(): Iterator<Attribute>
    {
        return array.get().iterator();
    }

    override fun excludeAll(): Array<out Attribute>
    {
        return array.getAndSet(emptyArray())
    }
}



class AttributeDomainArc (attribute: Attribute, domain: Domain) : Arc<Attribute,Domain> (attribute, domain)
{
    val attribute: Attribute get() = source
    val domain: Domain get() = target

    override fun drop()
    {
        source.domain = null
        super.drop()
    }
}


