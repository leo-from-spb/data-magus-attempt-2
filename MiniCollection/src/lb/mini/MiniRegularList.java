package lb.mini;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

import static lb.mini.Mini.emptyList;
import static lb.mini.Mini.listOf;
import static lb.mini.MiniInternals.ArrayIterator;
import static lb.mini.MiniInternals.suggestNewCapacity;



/**
 * @author Leonid Bushuev
 **/
@SuppressWarnings("unchecked")
final class MiniRegularList<E> extends MiniList<E> {

  //// STATE \\\\

  /**
   * Array with elements.
   */
  @NotNull
  final Object[] array;

  /**
   * The size of THIS list.
   * Non-zero.
   */
  final int size;


  //// CONSTRUCTOR \\\\


  MiniRegularList(@NotNull final Object[] array, int offset, int size, boolean copy) {
    copy = copy || offset != 0;
    this.array = copy ? copyArray(array, offset, size) : array;
    this.size = size;
  }

  @NotNull
  static private Object[] copyArray(@NotNull final Object[] array, int offset, int size) {
    int capacity = suggestNewCapacity(size);
    Object[] a = new Object[capacity];
    System.arraycopy(array, offset, a, 0, size);
    return a;
  }


  //// MANIPULATIONS \\\\

  @NotNull
  @Override
  public MiniRegularList<E> grow(@NotNull final E element) {
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
    Object[] a = new Object[newCapacity];
    System.arraycopy(array, 0, a, 0, size);
    a[size] = element;
    return new MiniRegularList<>(a, 0, size + 1, false);
  }


  @NotNull
  @Override
  public Couple<MiniList<E>> splitAt(int position) {
    if (position == 0) return Couple.of(emptyList(), this);
    if (position == size) return Couple.of(this, emptyList());
    if (position < 0 || position > size) throw new IndexOutOfBoundsException("Attempted to split a list of "+size+" elements at position " + position);

    MiniList<E> a = listOf(array, 0, position, false);
    MiniList<E> b = listOf(array, position, size, false);
    return Couple.of(a, b);
  }


  @NotNull
  @Override
  public Couple<MiniList<E>> splitWhen(@NotNull Predicate<E> predicate) {
    MiniBuilder<E> builderA = new MiniBuilder<>(size);
    MiniBuilder<E> builderB = new MiniBuilder<>(size);
    for (int i = 0; i < size; i++) {
      E element = (E)array[i];
      if (predicate.test(element)) builderA.add(element);
      else                         builderB.add(element);
    }

    if (builderA.isEmpty()) return Couple.of(emptyList(), this);
    if (builderB.isEmpty()) return Couple.of(this, emptyList());

    return Couple.of(builderA.buildList(), builderB.buildList());
  }


  @NotNull
  @Override
  public MiniList<E> except(@Nullable Object element) {
    if (element == null) return this;

    int firstIndex = indexOf(element);
    if (firstIndex < 0) return this;

    MiniBuilder b = new MiniBuilder(size - 1);
    b.addAll(array, 0, firstIndex, false);
    for (int i = firstIndex+1; i < size; i++) {
      Object ei = array[i];
      if (!ei.equals(element)) b.add(ei);
    }
    return b.buildList();
  }


  //// METHODS \\\\


  @NotNull
  @Override
  public E getFirst() {
    return (E)array[0];
  }


  @NotNull
  @Override
  public E getLast() {
    return (E)array[size-1];
  }


  @NotNull
  @Override
  public E get(int index) {
    if (index >= 0 && index < size) return (E)array[index];
    else throw new IndexOutOfBoundsException("Attempted to access element "+index+" when the list has only "+size+" elements");
  }


  @Override
  public int size() {
    return this.size;
  }


  @Contract(value = " -> false", pure = true)
  @Override
  public boolean isEmpty() {
    return false;
  }


  @Contract(value = " -> true", pure = true)
  @Override
  public boolean isNotEmpty() {
    return true;
  }


  @Override
  public int indexOf(final Object o) {
    return MiniInternals.indexOf(o, array, size);
  }


  @Override
  public int lastIndexOf(final Object o) {
    return MiniInternals.lastIndexOf(o, array, size);
  }


  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }


  @NotNull
  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    if (fromIndex < 0 || toIndex > size)
      throw new IndexOutOfBoundsException(String.format("Attempted a subList of %d..%d from a list of %d elements",
                                                        fromIndex,toIndex,size));

    int n = toIndex - fromIndex;
    if (n <= 0) return MiniEmptyList.one();
    if (n == 1) return new MiniSingletonList((E)array[fromIndex]);
    if (n == size) return this;

    return new MiniRegularList<>(array, fromIndex, n, true);
  }


  @NotNull
  @Override
  public Iterator<E> iterator() {
    return new ArrayIterator<>(array, 0, size);
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator() {
    return new ArrayIterator<>(array, 0, size);
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator(int index) {
    return new ArrayIterator<>(array, 0, size, index);
  }
}
