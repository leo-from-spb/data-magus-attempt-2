package lb.dmagus.model.core


/**
 * @author Leonid Bushuev from JetBrains
 **/
public abstract class Family<out P: Node, out C: Element>
{
    public abstract val owner: P


}