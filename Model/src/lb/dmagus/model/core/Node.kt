package lb.dmagus.model.core

/**
 * @author Leonid Bushuev from JetBrains
 **/
public interface Node
{

    val parentNode: Node?

    val childNodes: Iterable<Node>

}