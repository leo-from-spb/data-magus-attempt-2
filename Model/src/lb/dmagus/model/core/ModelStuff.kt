package lb.dmagus.model.core

import java.util.*


public object ComponentNameOrder: Comparator<Component>
{
    override fun compare(comp1: Component?, comp2: Component?): Int
    {
        if (comp1 === comp2) return 0;
        if (comp1 == null) return +1;
        if (comp2 == null) return -1;

        val name1 = comp1.name;
        val name2 = comp2.name;
        val z = if (name1 === name2)    0
                else if (name1 == null) +1
                else if (name2 == null) -1
                else String.CASE_INSENSITIVE_ORDER.compare(name1, name2);

        return if (z == 0) comp1.id - comp2.id else z;
    }
}