package lb.dmagus.model.concept

import lb.dmagus.model.core.Family
import lb.dmagus.model.core.Model
import lb.dmagus.model.core.Node
import lb.dmagus.model.core.TopSpace
import java.util.concurrent.atomic.AtomicReference

public open class SubjectArea : ConceptElement , TopSpace
{
    

    // +TopSpace

    constructor(model: Model) : super(model)

    override val parentNode: Node
        get() = model

    // -TopSpace

    // +Families

    
    public val domains: DomainFamily = DomainFamily(this)

    public val entities: EntityFamily = EntityFamily(this)



    val families = arrayOf(domains,entities)

    // -Families

    

}


public class SubjectAreaFamily : Family<Model, SubjectArea>
{

    val model: Model

    override val owner: Model
        get() = model


    private val array: AtomicReference<Array<SubjectArea>> = AtomicReference(emptyArray())


    constructor(model: Model) : super()
    {
        this.model = model
    }




    public fun newSubjectArea(): SubjectArea
    {
        val subjectArea = SubjectArea(model)
        val subjectAreaInArray = arrayOf(subjectArea)

        do {
            val oldArray = array.get();
            val newArray = oldArray + subjectAreaInArray;
            val ok =
                array.compareAndSet(oldArray, newArray)
        } while (!ok)

        return subjectArea
    }

}