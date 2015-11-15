package lb.dmagus.model.concept

import lb.dmagus.model.core.*
import java.util.concurrent.atomic.AtomicReference


/**
 * The entity.
 *
 * <p>
 * This class is generated by Gena the Crocodile.
 * Don't modify it manually.
 * </p>
 **/
public open class Entity : ConceptElement 
{


    //// LIFE CYCLE STUFF \\\\

    val subjectArea: SubjectArea

    constructor(subjectArea: SubjectArea) : super(subjectArea.model)
    {
        this.subjectArea = subjectArea
    }

    override val parentNode: Node
        get() = subjectArea




    //// FAMILIES \\\\

    public val attributes: AttributeFamily = AttributeFamily(this)

    override val families =
        listOf(attributes)

    override val childNodes: Iterable<Node>
        get() = attributes







    //// NO PROPERTIES \\\\





}


/**
 * The entity family.
 *
 * <p>
 * This class is generated by Gena the Crocodile.
 * Don't modify it manually.
 * </p>
 **/
public class EntityFamily : Family<SubjectArea, Entity>
{

    val subjectArea: SubjectArea

    override val owner: SubjectArea
        get() = subjectArea


    private val array: AtomicReference<Array<Entity>> = AtomicReference(emptyArray())


    constructor(subjectArea: SubjectArea) : super()
    {
        this.subjectArea = subjectArea
    }


    public fun newEntity(): Entity
    {
        val entity = Entity(subjectArea)
        val entityInArray = arrayOf(entity)

        do {
            val oldArray = array.get();
            val newArray = oldArray + entityInArray;
            val ok =
                array.compareAndSet(oldArray, newArray)
        } while (!ok)

        return entity
    }

    override fun iterator(): Iterator<Entity>
    {
        return array.get().iterator();
    }
}



