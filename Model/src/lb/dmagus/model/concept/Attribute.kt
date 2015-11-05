package lb.dmagus.model.concept

import lb.dmagus.model.core.Node

/**
 * @author Leonid Bushuev from JetBrains
 **/
class Attribute : ConceptElement
{

    val entity: Entity

    constructor(entity: Entity) : super(entity.model)
    {
        this.entity = entity
    }

    override val parentNode: Node
        get() = entity

}