package lb.dmagus.collections

import java.util.concurrent.atomic.AtomicReference
import kotlin.jvm.internal.Intrinsic
import kotlin.reflect.KClass

/**
 * Copy-on-write array list, that is designed for keeping model entities.
 *
 * @author Leonid Bushuev from JetBrains
 **/
class ArrayCloneList<E: Any>: List<E>
{
    private val array: AtomicReference<Array<E>?> = AtomicReference(null)
    val elementClass: Class<E>


    constructor(elementClass: KClass<E>)
    {
        this.elementClass = elementClass.java
    }


    private inline fun change (modifier: (Array<E>?) -> Array<E>?)
    {
        var ok: Boolean
        do {
            val old = array.get()
            val neo = modifier(old)
            if (old === neo) return
            ok = array.compareAndSet(old, neo)
        } while (!ok)
    }

    fun add(element: E): Int
    {
        var result: Int = Int.MIN_VALUE

        change { old ->
            if (old == null) {
                result = 0
                @Suppress("UNCHECKED_CAST")
                val neo = java.lang.reflect.Array.newInstance(elementClass, 1) as Array<E>
                neo[0] = element
                neo
            }
            else {
                val n = old.size
                result = n
                @Suppress("UNCHECKED_CAST")
                val neo = java.lang.reflect.Array.newInstance(elementClass, n + 1) as Array<E>
                System.arraycopy(old, 0, neo, 0, n)
                neo[n] = element
                neo
            }
        }

        return result
    }

    fun remove(element: E)
    {
        change { old ->
          if (old == null) {
              null
          }
          else {
              val index = old.indexOf(element)
              if (index < 0) {
                  old
              }
              else {
                  val n = old.size
                  if (n == 1) {
                      null
                  }
                  else {
                      @Suppress("UNCHECKED_CAST")
                      val neo = java.lang.reflect.Array.newInstance(elementClass, n - 1) as Array<E>
                      if (index > 0) System.arraycopy(old, 0, neo, 0, index)
                      if (index < n) System.arraycopy(old, index+1, neo, index, n-index-1)
                      neo
                  }
              }
          }
        }
    }


    fun excludeAt(index: Int): E
    {
        var result: E? = null

        change { old ->
            old ?: throw IndexOutOfBoundsException("Array is empty when attempted to exclude element number $index")
            val n = old.size
            result = old[index]
            if (index == 0 && n == 1) {
                null
            }
            else {
                @Suppress("UNCHECKED_CAST")
                val neo = java.lang.reflect.Array.newInstance(elementClass, n - 1) as Array<E>
                if (index > 0) System.arraycopy(old, 0, neo, 0, index)
                if (index < n) System.arraycopy(old, index+1, neo, index, n-index-1)
                neo
            }
        }

        return result ?: throw InternalError("Internal error")
    }


    fun excludeAll(): Array<E>
    {
        var result: Array<E>? = null

        change { old ->
            result = old ?: (emptyArray<Any>() as Array<E>)
            null
        }

        return result ?: throw InternalError("Internal error")
    }


    //// LIST IMPLEMENTATION \\\\

    override val size: Int
        get() = array.get()?.size?:0

    override fun isEmpty(): Boolean
    {
        return array.get() == null
    }

    override fun contains(element: E): Boolean
    {
        return array.get()?.contains(element)?:false
    }

    override fun iterator(): Iterator<E>
    {
        return array.get()?.iterator()?:emptyList<E>().iterator()
    }

    override fun containsAll(elements: Collection<E>): Boolean
    {
        val a = array.get()
        return if (a != null) a.asList().containsAllRaw(elements) else false
    }

    override fun get(index: Int): E
    {
        val a = array.get() ?: throw IndexOutOfBoundsException("The list is empty when asking an element number $index")
        return a[index]
    }

    override fun indexOf(element: E): Int
    {
        val a = array.get() ?: return -1
        return a.indexOf(element)
    }

    override fun lastIndexOf(element: E): Int
    {
        val a = array.get() ?: return -1
        return a.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<E>
    {
        val a = array.get() ?: return emptyList<E>().listIterator()
        return a.asList().listIterator()
    }

    override fun listIterator(index: Int): ListIterator<E>
    {
        val a = array.get() ?: return emptyList<E>().listIterator()
        return a.asList().listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<E>
    {
        val a = array.get() ?: return emptyList()
        return a.asList().subList(fromIndex, toIndex)
    }


}