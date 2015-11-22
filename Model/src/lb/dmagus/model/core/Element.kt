package lb.dmagus.model.core

import java.util.*
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
        clearAllArcs()
        clearAllFamilies()
    }

    val dropped: Boolean get() = id == Int.MIN_VALUE;


    fun modifying()
    {
        model.modifying(this)
    }


    //// FAMILIES \\\\

    public abstract val families: List<Family<Node,Element>>


    private fun clearAllFamilies()
    {
        for (f in families)
        {
            f.clear()
        }
    }


    //// REFERENCES \\\\

    inner abstract class References<S: Element, T: Element>: Sequence<T>
    {
        val arcs: ArrayList<Arc<S,T>> = ArrayList()

        override fun iterator(): Iterator<T>
        {
            return arcs.map { it.target }.iterator()
        }

        fun assign(elements: Collection<T>)
        {
            if (arcs.isEmpty() && elements.isEmpty()) return;
            this@Element.modifying()

            clear();

            if (elements.isNotEmpty()) {
                arcs.ensureCapacity(elements.size);
                for (element in elements)
                {
                   val arc = newArc(element)
                    arcs.add(arc)
                }
            }

        }

        protected abstract fun newArc(target: T): Arc<S,T>;

        internal fun unregister(arc: Arc<*,*>)
        {
            for (i in arcs.size-1 downTo 0) if (arcs[i] === arc) arcs.removeAt(i)
        }

        fun clear()
        {
            for (i in arcs.size-1 downTo 0) arcs[i].drop()
        }
    }




    //// ARCS \\\\

    private val arcs_ = ConcurrentHashMap<Arc<*,*>, Boolean>()

    internal fun registerArc(arc: Arc<*,*>)
    {
        assert(arc.source == this || arc.target == this) { "An alien arc!" }
        modifying()
        arcs_.putIfAbsent(arc, java.lang.Boolean.TRUE)
    }

    internal fun deregisterArc(arc: Arc<*,*>)
    {
        if (arcs_.isEmpty()) return

        modifying()
        arcs_.remove(arc)
    }

    /**
     * All arcs, both directions.
     */
    public val arcs: Collection<Arc<*,*>>
        get() = arcs_.keys

    private fun clearAllArcs()
    {
        if (arcs_.isEmpty()) return

        modifying()
        val arcsToDrop = arcs_.keys().toList()
        arcs_.clear()
        for (arc in arcsToDrop) arc.drop()
    }


    //// LEGACY FUNCTIONS \\\\

    override fun toString(): String {
        return "#$id"
    }
}