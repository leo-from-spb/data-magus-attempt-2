package lb.dmagus.model.core


/**
 * @author Leonid Bushuev from JetBrains
 **/
abstract class Family<out P: Node, out C: Element> : Iterable<C>
{
    public abstract val owner: P



}



