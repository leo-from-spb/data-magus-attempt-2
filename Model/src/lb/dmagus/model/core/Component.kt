package lb.dmagus.model.core

/**
 * Meaningful component of the model. Has name.
 *
 * @author Leonid Bushuev from JetBrains
 **/
public abstract class Component : Element()
{
    //// NAME \\\\

    /**
     * Name and identifier.
     * Designed for allowing to navigate objects by a part of name.
     */
    var nameId: NameId? = null
        private set;

    /**
     * Name of the component.
     */
    var name: String? = null
        private set (newName: String?)
        {
            assert(newName == null || newName.length > 0) {"Name cannot be empty; use null instead"}

            if (field == newName) return; // already such name

            if (nameId != null)
            {
                model?.deregisterName(this);
                nameId = null;
                field = null;
            }

            if (newName != null) {
                field = newName;
                nameId = NameId(newName, id);
                model?.registerName(this);
            }
        }




}

