package lb.dmagus.model.concept

import lb.dmagus.model.core.Node

/**
 * @author Leonid Bushuev from JetBrains
 **/
public class Entity : ConceptElement()
{

    var area: SubjectArea? = null;

    override val parentNode: Node?
        get() = area;

}