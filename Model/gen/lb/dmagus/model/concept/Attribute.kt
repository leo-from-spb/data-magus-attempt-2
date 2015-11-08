package lb.dmagus.model.concept

import lb.dmagus.model.core.Family
import lb.dmagus.model.core.Node
import java.util.concurrent.atomic.AtomicReference

public open class Attribute : ConceptElement 
{
    // +Normal

    val entity: Entity

    constructor(entity: Entity) : super(entity.model)
    {
        this.entity = entity
    }

    override val parentNode: Node
        get() = entity

    // -Normal

    






}


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

}