package lb.dmagus.model.core


/**
 * @author Leonid Bushuev from JetBrains
 **/
abstract class Family<out P: Node, out C: Element> : Iterable<C>
{
    abstract val owner: P

    abstract fun excludeAll(): Array<out C>

    fun clear()
    {
        val elementsToDrop = excludeAll()
        for (i in elementsToDrop.size-1 downTo 0)
            elementsToDrop[i].drop()
    }

}



