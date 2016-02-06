package lb.mini;

import lb.mini.exception.IllegalModificationException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;



/**
 * The base constant collection class.
 *
 * @author Leonid Bushuev
 **/
public abstract class MiniCollection<E> implements Collection<E>
{

  //// INTERNAL METHODS \\\\

  @NotNull
  abstract Object[] array();



  //// PRIMARY API \\\\


  @Override
  public abstract int size();



  //// API AND DEFAULT IMPLEMENTATION \\\\


  @Override
  public abstract boolean isEmpty();

  public abstract boolean isNotEmpty();

  @Override
  public abstract boolean contains(Object o);


  @Override
  public boolean containsAll(Collection c) {
    for (Object o : c) {
      if (!contains(o)) return false;
    }
    return true;
  }

  @NotNull
  @Override
  public Iterator<E> iterator() {
    return new MiniInternals.ArrayIterator<>(array(), 0, size());
  }


  @Override
  public Spliterator<E> spliterator() {
    return Spliterators.<E>spliterator(array(), 0, size(), MiniInternals.LIST_SPLITERATORS_CHARACTERISTICS);
  }


  @NotNull
  @Override
  public Object[] toArray() {
    final int n = size();
    return MiniInternals.copyArray(array(), 0, n, n);
  }


  @NotNull
  @Override
  public <T> T[] toArray(@NotNull T[] a) {
    final int n = size();
    int capacity = a.length;
    if (n <= capacity) {
      System.arraycopy(array(), 0, a, 0, n);
      if (n < capacity) Arrays.fill(a, n, capacity, null);
      return a;
    }
    else {
      final Class<?> componentType = a.getClass().getComponentType();
      //noinspection unchecked
      T[] newArray = (T[]) Array.newInstance(componentType, n);
      //noinspection SuspiciousSystemArraycopy
      System.arraycopy(array(), 0, newArray, 0, n);
      return newArray;
    }
  }



  //// FORBIDDEN MUTABILITY \\\\


  @Contract("_ -> fail")
  @Override
  public final boolean add(E o) {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @Override
  public final boolean remove(Object o) {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @SuppressWarnings("NullableProblems")
  @Override
  public final boolean addAll(Collection<? extends E> c) {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @SuppressWarnings("NullableProblems")
  @Override
  public final boolean removeAll(Collection<?> c) {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @SuppressWarnings("NullableProblems")
  @Override
  public final boolean retainAll(Collection<?> c) {
    throw new IllegalModificationException();
  }


  @Contract(" -> fail")
  @Override
  public final void clear() {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @Override
  public final boolean removeIf(Predicate<? super E> filter) {
    throw new IllegalModificationException();
  }

}
