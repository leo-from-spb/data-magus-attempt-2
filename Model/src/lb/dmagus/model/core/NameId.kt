package lb.dmagus.model.core

/**
 * @author Leonid Bushuev from JetBrains
 **/
public data class NameId (val name: String, val id: Int) : Comparable<NameId>
{
    override fun compareTo(other: NameId): Int
    {
        return compare(this, other);
    }

    override fun toString(): String
    {
        return "$name($id)";
    }
}

private fun compare(a: NameId, b: NameId): Int
{
    if (a === b) return 0;
    val z = String.CASE_INSENSITIVE_ORDER.compare(a.name, b.name);
    return if (z == 0) a.id - b.id else z;
}

