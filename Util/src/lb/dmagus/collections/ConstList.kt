package lb.dmagus.collections

import java.util.*

/**
 * @author Leonid Bushuev from JetBrains
 **/
class ConstList<out T> : List<T>
{
    private val array: Array<out T>

    private val count: Int


    @Suppress("CAST_NEVER_SUCCEEDS")
    constructor(vararg array: T)
    {
        if (array.size > 0)
        {
            count = array.size;
            this.array = Arrays.copyOf(array, count)
        }
        else
        {
            // useless case
            this.array = emptyArray<Any>() as Array<T>
            count = 0
        }
    }


    override val size: Int get() = count

    override fun isEmpty() = count == 0

    override fun get(index: Int): T = array[index]

    override fun subList(fromIndex: Int, toIndex: Int): List<T>
    {
        throw NotImplementedError("ConstList.subList() is not implemented yet")
    }

    override fun indexOf(element: @UnsafeVariance T): Int
    {
        for (i in 0..count-1) if (array[i] == element) return i;
        return -1
    }

    override fun lastIndexOf(element: @UnsafeVariance T): Int
    {
        for (i in count-1 downTo 0) if (array[i] == element) return i;
        return -1
    }

    override fun contains(element: @UnsafeVariance T): Boolean
    {
        for (i in 0..count-1) if (array[i] == element) return true;
        return false
    }

    override fun containsAll(elements: Collection<@UnsafeVariance T>): Boolean
    {
        throw NotImplementedError("ConstList.containsAll() is not implemented yet")
    }

    override fun iterator(): Iterator
    {
        return Iterator()
    }

    override fun listIterator(): ListIterator<T>
    {
        return Iterator()
    }

    override fun listIterator(index: Int): ListIterator<T>
    {
        return Iterator(index)
    }

    public inner class Iterator (var index: Int = 0) : kotlin.ListIterator<T>
    {

        override fun next(): T
        {
            if (index < count)
            {
                return array[index++]
            }
            else
            {
                throw IllegalStateException("The end of the list is reached")
            }
        }

        override fun previous(): T
        {
            if (index > 0)
            {
                return array[--index]
            }
            else
            {
                throw IllegalStateException("The begin of the list is reached")
            }
        }

        override fun hasNext(): Boolean
        {
            return index < count
        }

        override fun hasPrevious(): Boolean
        {
            return index > 0
        }

        override fun nextIndex(): Int
        {
            return index
        }

        override fun previousIndex(): Int
        {
            return index-1
        }
    }
}


