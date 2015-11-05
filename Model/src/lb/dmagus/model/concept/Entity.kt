package lb.dmagus.model.concept

import lb.dmagus.model.core.Node

/**
 * @author Leonid Bushuev from JetBrains
 **/
public class Entity : ConceptElement
{

    //// STATE \\\\

    var area: SubjectArea;




    //// LIFE CYCLE \\\\

    constructor(area: SubjectArea) : super(area.model)
    {
        this.area = area
    }

    override val parentNode: Node
        get() = area;

}