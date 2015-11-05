package lb.dmagus.model.core

/**
 * @author Leonid Bushuev from JetBrains
 **/
public interface TopElement : Node
{

    override val model: Model?;

    override val parentNode: Node?
        get() {return model};

}