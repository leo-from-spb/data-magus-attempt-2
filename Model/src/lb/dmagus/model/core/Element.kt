package lb.dmagus.model.core

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
    override var model: Model? = null
        private set;

    /**
     * Synthetic identifier.
     * Zero means this element is not attached to a model.
     */
    var id: Int = 0
        private set;


    //// LIFE CYCLE METHODS \\\\

    fun attach(model: Model)
    {
        assert(this.model == null);
        this.model = model;
        this.id = model.nextId();
        model.registerElement(this);
    }

    fun detach()
    {
        val m = model;
        if (m != null)
        {
            m.deregisterElement(this);
            model = null;
        }
    }





    //// LEGACY FUNCTIONS \\\\

    override fun toString(): String {
        return "#$id"
    }
}