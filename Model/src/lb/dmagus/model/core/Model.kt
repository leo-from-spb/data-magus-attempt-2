package lb.dmagus.model.core

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentSkipListMap
import java.util.concurrent.atomic.AtomicInteger

/**
 * Project model. Includes all conceptual and physical models, and albums with graphics.
 *
 * @author Leonid Bushuev from JetBrains
 **/
public class Model : Node
{

    //// INTERNAL STATE \\\\

    private val idCounter: AtomicInteger = AtomicInteger(0);

    private val elements: ConcurrentHashMap<Int,Element> = ConcurrentHashMap(4096);

    private val names: ConcurrentSkipListMap<NameId,Component> = ConcurrentSkipListMap();


    //// INTERNAL METHODS \\\\

    fun nextId(): Int
    {
        return idCounter.incrementAndGet();
    }

    fun registerElement(element: Element)
    {
        val id = element.id;
        assert (id > 0) {"Model: Registering element: id must be greater than zero but got $id"};
        val was =
            elements.putIfAbsent(id, element);
        assert (was == null) {"Model: Registering element: attempting to register an element ($element) but an element with the same id already exists ($was)"};
    }

    fun deregisterElement(element: Element)
    {
        elements.remove(element.id, element);
    }

    fun registerName(component: Component)
    {
        val ni = component.nameId;
        if (ni != null)
            names.put(ni, component)
    }

    fun deregisterName(component: Component)
    {
        val ni = component.nameId;
        if (ni != null)
            names.remove(ni, component)
    }


    //// TREE \\\\

    override val parentNode: Node?
        get() = null;



    //// STATISTICS \\\\

    val elementCount: Int
        get() = elements.size


}