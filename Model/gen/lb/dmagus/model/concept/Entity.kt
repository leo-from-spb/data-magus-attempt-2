package lb.dmagus.model.concept

import lb.dmagus.model.core.Family
import lb.dmagus.model.core.Node
import java.util.concurrent.atomic.AtomicReference

public open class Entity : ConceptElement 
{
    // +Normal

    val subjectArea: SubjectArea

    constructor(subjectArea: SubjectArea) : super(subjectArea.model)
    {
        this.subjectArea = subjectArea
    }

    override val parentNode: Node
        get() = subjectArea

    // -Normal

    

    // +Families

    
    public val attributes: AttributeFamily = AttributeFamily(this)



    val families = arrayOf(attributes)

    // -Families

    

}


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

}