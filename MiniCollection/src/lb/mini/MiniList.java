package lb.mini;

import lb.mini.exception.CollectionIsEmptyException;
import lb.mini.exception.IllegalModificationException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static lb.mini.Mini.empty;
import static lb.mini.Mini.listOf;
import static lb.mini.MiniInternals.suggestNewCapacity;



/**
 * @author Leonid Bushuev
 **/
public abstract class MiniList<E> extends MiniCollection<E> implements List<E>, RandomAccess {

  //// ACCESSORS \\\\

  /**
   * The first element of the list.
   * @return the first element.
   * @throws CollectionIsEmptyException when the collection is empty.
   */
  @NotNull
  public abstract E getFirst();

  /**
   * The first element of the list.
   * @return the first element.
   * @throws CollectionIsEmptyException when the collection is empty.
   */
  @NotNull
  public abstract E getLast();

  @Override
  @NotNull
  public abstract E get(int index);



  //// MANIPULATIONS \\\\


  /**
   * Creates a new list that contains all items from this one
   * plus one more <tt>element</tt>.
   * @param element the element to add to the list.
   * @return        the result list.
   */
  @NotNull
  public MiniList<E> join(@NotNull final E element) {
    final Object[] array = array();
    final int size = size();

    // first, try the optimized way
    if (size < array.length) {
      synchronized (array) {
        if (array[size] == null) {
          array[size] = element; // captured
          return new MiniRegularList<>(array, 0, size + 1, false);
        }
      }
    }

    // someone has already captured this array or we've reached the end, so we have to make a copy
    int newCapacity = suggestNewCapacity(size+1);
    Object[] newArray = new Object[newCapacity];
    System.arraycopy(array, 0, newArray, 0, size);
    newArray[size] = element;
    return new MiniRegularList<>(newArray, 0, size + 1, false);
  }

  /**
   * Splits the list at the specified position.
   * @param position  the position for splitting.
   * @return          a couple of lists.
   */
  @NotNull
  public Couple<? extends MiniList<E>> splitAt(int position) {
    final int size = size();
    if (position == 0) return Couple.of(MiniEmpty.one(), this);
    if (position == size) return Couple.of(this, MiniEmpty.one());
    if (position < 0 || position > size) throw new IndexOutOfBoundsException("Attempted to split a list of "+size+" elements at position " + position);

    final Object[] array = array();
    MiniList<E> a = listOf(array, 0, position, false);
    MiniList<E> b = listOf(array, position, size, false);
    return Couple.of(a, b);
  }

  /**
   * Splits a list into two lists,
   * the first list with items that match the predicate (met the condition),
   * and the second list with the rest of items (that don't match the predicate).
   * @param predicate  the predicate.
   * @return           the couple of lists:
   *                     {@link Couple#a} items that met the condition,
   *                     {@link Couple#b} items that don't met the condition.
   *                   Both list are not null, but they can be empty.
   */
  @NotNull
  public Couple<? extends MiniList<E>> splitWhen(@NotNull Predicate<E> predicate) {
    final int size = size();
    MiniBuilder<E> builderA = new MiniBuilder<>(size);
    MiniBuilder<E> builderB = new MiniBuilder<>(size);

    final Object[] array = array();
    for (int i = 0; i < size; i++) {
      //noinspection unchecked
      E element = (E)array[i];
      if (predicate.test(element)) builderA.add(element);
      else                         builderB.add(element);
    }

    if (builderA.isEmpty()) return Couple.of(empty(), this);
    if (builderB.isEmpty()) return Couple.of(this, empty());

    return Couple.of(builderA.buildList(), builderB.buildList());
  }

  /**
   * Returns the list without the given element.
   * @param element  undesired element.
   * @return         the list that doesn't contain the given <tt>element</tt>.
   */
  @NotNull
  public MiniList<E> except(@Nullable Object element) {
    if (element == null) return this;

    int firstIndex = indexOf(element);
    if (firstIndex < 0) return this;

    final int size = size();
    final Object[] array = array();
    MiniBuilder<E> b = new MiniBuilder<>(size - 1);
    b.addAll(array, 0, firstIndex, false);
    for (int i = firstIndex+1; i < size; i++) {
      //noinspection unchecked
      E ei = (E) array[i];
      if (!ei.equals(element)) b.add(ei);
    }
    return b.buildList();
  }



  //// DEFAULT IMPLEMENTATION \\\\

  @Override
  public int indexOf(final Object o) {
    return MiniInternals.indexOf(o, array(), size());
  }


  @Override
  public int lastIndexOf(final Object o) {
    return MiniInternals.lastIndexOf(o, array(), size());
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  @NotNull
  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    final int size = size();
    if (fromIndex < 0 || toIndex > size)
      throw new IndexOutOfBoundsException(String.format("Attempted a subList of %d..%d from a list of %d elements",
                                                        fromIndex,toIndex,size));

    int n = toIndex - fromIndex;
    if (n <= 0) return MiniEmpty.one();
    if (n == 1) return new MiniSingleton<>(get(fromIndex));
    if (n == size) return this;

    return new MiniRegularList<>(array(), fromIndex, n, true);
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator() {
    return new MiniInternals.ArrayIterator<>(array(), 0, size());
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator(int index) {
    return new MiniInternals.ArrayIterator<>(array(), 0, size(), index);
  }


  @Override
  public Spliterator<E> spliterator() {
    return Spliterators.spliterator(this, Spliterator.SIZED | Spliterator.NONNULL | Spliterator.ORDERED | Spliterator.IMMUTABLE);
  }



  //// FORBIDDEN MUTABILITY \\\\


  @Contract("_, _ -> fail")
  @Override
  public final boolean addAll(int index, Collection<? extends E> c) {
    throw new IllegalModificationException();
  }


  @Contract("_, _ -> fail")
  @Override
  public final E set(int index, E element) {
    throw new IllegalModificationException();
  }


  @Contract("_, _ -> fail")
  @Override
  public final void add(int index, E element) {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @Override
  public final E remove(int index) {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @Override
  public final void replaceAll(UnaryOperator<E> operator) {
    throw new IllegalModificationException();
  }


  @Contract("_ -> fail")
  @Override
  public final void sort(Comparator<? super E> c) {
    throw new IllegalModificationException();
  }
}
