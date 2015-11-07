package lb.dmagus.model.concept

import lb.dmagus.model.core.Model
import lb.dmagus.model.core.TopElement

/**
 * @author Leonid Bushuev from JetBrains
 **/
public class SubjectArea : ConceptElement, TopElement
{

    constructor(model: Model) : super(model)

    override val parentNode: Model
        get() = model;


    var prefix: String? = null
        set(newPrefix) {
            if (field == newPrefix) return
            field = newPrefix
        }


}