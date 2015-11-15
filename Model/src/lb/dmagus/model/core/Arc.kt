package lb.dmagus.model.core

/**
 * Arc — a directed link between two nodes of the tree,
 * that turn this tree into a graph.
 *
 * @author Leonid Bushuev from JetBrains
 **/
public abstract class Arc <out S: Element, out T: Element>
{
    //// STATE \\\\

    /**
     * The first end of the arc.
     */
    val source: S

    /**
     * The second end of the arc.
     */
    val target: T



    //// LIFECYCLE \\\\

    constructor(source: S, target: T)
    {
        this.source = source
        this.target = target

        source.registerArc(this)
        target.registerArc(this)
    }


    open fun drop()
    {
        target.deregisterArc(this)
        source.deregisterArc(this)
    }


    //// USEFUL FUNCTIONS \\\\

    val shortLoop: Boolean get() = source == target
}




