package lb.dmagus.model.core

/**
 * @author Leonid Bushuev from JetBrains
 **/
public interface Node
{

    val model: Model?;

    val parentNode: Node?;

}