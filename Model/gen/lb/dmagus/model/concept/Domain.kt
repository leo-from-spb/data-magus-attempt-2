package lb.dmagus.model.concept

import lb.dmagus.model.core.Family
import lb.dmagus.model.core.Node
import java.util.concurrent.atomic.AtomicReference

public open class Domain : ConceptElement 
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

    

    

    // +Properties

    

    var abstract: Boolean = false
        set (value) {
            if (field == value) return
            modifying()
            field = value
        }



    // -Properties

}


public class DomainFamily : Family<SubjectArea, Domain>
{

    val subjectArea: SubjectArea

    override val owner: SubjectArea
        get() = subjectArea


    private val array: AtomicReference<Array<Domain>> = AtomicReference(emptyArray())


    constructor(subjectArea: SubjectArea) : super()
    {
        this.subjectArea = subjectArea
    }




    public fun newDomain(): Domain
    {
        val domain = Domain(subjectArea)
        val domainInArray = arrayOf(domain)

        do {
            val oldArray = array.get();
            val newArray = oldArray + domainInArray;
            val ok =
                array.compareAndSet(oldArray, newArray)
        } while (!ok)

        return domain
    }

}