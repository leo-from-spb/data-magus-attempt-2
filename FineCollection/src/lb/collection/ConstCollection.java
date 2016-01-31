package lb.collection;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;



/**
 * The base constant collection class.
 *
 * @author Leonid Bushuev
 **/
public abstract class ConstCollection<E> implements Collection<E>
{

  @Override
  public abstract int size();


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
  public Object[] toArray() {
    // TODO implement ConstCollection.toArray()
    throw new RuntimeException("Method ConstCollection.toArray() is not implemented yet.");
  }


  @NotNull
  @Override
  public <T> T[] toArray(T[] a) {
    // TODO implement ConstCollection.toArray()
    throw new RuntimeException("Method ConstCollection.toArray() is not implemented yet.");
  }


  @NotNull
  @Override
  public abstract Iterator<E> iterator();



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
