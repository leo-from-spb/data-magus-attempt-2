package lb.dmagus.model.core

import java.util.concurrent.ConcurrentHashMap


/**
 * @author Leonid Bushuev from JetBrains
 **/
public abstract class Element : Node
{
    //// STATE \\\\

    /**
     * The model this element belongs to.
     * Null means this element is not attached to a model.
     */
    @JvmField
    val model: Model;

    /**
     * Synthetic identifier.
     * Zero means this element is not attached to a model.
     */
    var id: Int
        private set;





    //// LIFE CYCLE METHODS \\\\

    constructor(model: Model)
    {
        this.model = model
        this.id = model.nextId()
        model.registerElement(this)
    }

    fun drop()
    {
        dropInnerContent();
        model.deregisterElement(this)
        id = Int.MIN_VALUE;
    }

    open fun dropInnerContent()
    {
        // here do nothing, inheritors have something
    }

    val dropped: Boolean get() = id == Int.MIN_VALUE;


    fun modifying()
    {
        model.modifying(this)
    }


    //// FAMILIES \\\\

    public abstract val families: List<Family<Node,Element>>


    //// ARCS \\\\

    private val arcs = ConcurrentHashMap<Arc<*,*>, Boolean>()

    internal fun registerArc(arc: Arc<*,*>)
    {
        assert(arc.source == this || arc.target == this) { "An alien arc!" }
        modifying()
        arcs.putIfAbsent(arc, java.lang.Boolean.TRUE)
    }

    internal fun deregisterArc(arc: Arc<*,*>)
    {
        modifying()
        arcs.remove(arc)
    }


    //// LEGACY FUNCTIONS \\\\

    override fun toString(): String {
        return "#$id"
    }
}