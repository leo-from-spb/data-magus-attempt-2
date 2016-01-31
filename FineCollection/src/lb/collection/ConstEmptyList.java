package lb.collection;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;



/**
 * @author Leonid Bushuev
 **/
final class ConstEmptyList<E> extends ConstList<E> {


  //// THE INSTANCE \\\\

  private static final ConstEmptyList<?> INSTANCE = new ConstEmptyList();


  static <T> ConstList<T> one() {
    //noinspection unchecked
    return (ConstList<T>) INSTANCE;
  }


  //// MANIPULATIONS \\\\


  @NotNull
  @Override
  public ConstList<E> grow(@NotNull E element) {
    return new ConstSingletonList<>(element);
  }


  @NotNull
  @Override
  public Couple<ConstList<E>> splitAt(int position) {
    if (position != 0) throw new IndexOutOfBoundsException("Attempted to split an empty list on position " + position);
    return Couple.of(this,this);
  }

  @NotNull
  @Override
  public Couple<ConstList<E>> splitWhen(@NotNull Predicate<E> predicate) {
    return Couple.of(this,this);
  }


  @NotNull @Contract(pure = true)
  @Override
  public ConstList<E> except(@Nullable Object element) {
    return this;
  }

  //// API FUNCTIONS \\\\


  @NotNull @Contract(" -> fail")
  @Override
  public E getFirst() {
    throw new CollectionIsEmptyException();
  }


  @NotNull @Contract(" -> fail")
  @Override
  public E getLast() {
    throw new CollectionIsEmptyException();
  }


  @Override
  public int size() {
    return 0;
  }


  @Override
  public boolean isEmpty() {
    return true;
  }


  @Override
  public boolean isNotEmpty() {
    return false;
  }


  @Override
  public E get(int index) {
    throw new IndexOutOfBoundsException("Attempted to get an item from the empty list");
  }


  @Override
  public int indexOf(Object o) {
    return -1;
  }


  @Override
  public int lastIndexOf(Object o) {
    return -1;
  }


  @NotNull
  @Override
  public ConstList<E> subList(int fromIndex, int toIndex) {
    if (fromIndex == toIndex) return this;
    else throw new IndexOutOfBoundsException("Attempted to get a non-empty subList from the empty list");
  }


  @Override
  public boolean contains(Object o) {
    return false;
  }


  @Override
  public boolean containsAll(Collection c) {
    return c.isEmpty();
  }


  @NotNull
  @Override
  public Object[] toArray() {
    return ConstInternals.EMPTY_ARRAY;
  }


  @NotNull
  @Override
  public <T> T[] toArray(T[] a) {
    if (a.length > 0) Arrays.fill(a, null);
    return a;
  }


  @Override
  public void forEach(Consumer action) {
    // nothing to do
  }


  @NotNull
  @Override
  public Iterator<E> iterator() {
    return Collections.emptyIterator();
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator() {
    return Collections.emptyListIterator();
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator(int index) {
    return Collections.emptyListIterator();
  }


  @Override
  public Spliterator<E> spliterator() {
    return Spliterators.emptySpliterator();
  }

}
