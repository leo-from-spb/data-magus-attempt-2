package lb.mini;

import lb.mini.exception.IllegalModificationException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;



/**
 * Internal stuff used by constant collections.
 *
 * @author Leonid Bushuev
 **/
interface MiniInternals {

  static final Object[] EMPTY_ARRAY = new Object[0];


  //// ARRAYS AND SIZES \\\\

  @Contract(pure = true)
  static int suggestNewCapacity(int min) {
    if (min < 4) return 4;

    int v = 8;
    while (v <= min) v = v << 1;

    return v;
  }

  static int indexOf(final Object element, @NotNull final Object[] array, final int size) {
    for (int i = 0; i < size; i++) {
      if (array[i].equals(element)) return i;
    }
    return -1;
  }

  static int lastIndexOf(final Object element, @NotNull final Object[] array, final int size) {
    for (int i = size-1; i >= 0; i--) {
      if (array[i].equals(element)) return i;
    }
    return -1;
  }



  //// ITERATORS \\\\

  final class SingletonIterator<E> implements ListIterator<E> {

    @NotNull
    private final E element;

    private boolean begin;


    SingletonIterator(@NotNull final E element) {
      this.element = element;
      begin = true;
    }


    SingletonIterator(@NotNull final E element, boolean begin) {
      this.element = element;
      this.begin = begin;
    }


    public boolean hasNext() {
      return begin;
    }


    @NotNull
    public E next() {
      if (begin) {
        begin = false;
        return element;
      }
      else {
        throw new NoSuchElementException();
      }
    }


    @Override
    public boolean hasPrevious() {
      return !begin;
    }


    @Override
    public E previous() {
      if (!begin) {
        begin = true;
        return element;
      }
      else {
        throw new NoSuchElementException();
      }
    }


    @Override
    public int nextIndex() {
      return begin ? 0 : 1;
    }


    @Override
    public int previousIndex() {
      return begin ? -1 : 0;
    }


    @Contract("_ -> fail")
    public void add(E e) {
      throw new IllegalModificationException();
    }


    @Contract("_ -> fail")
    public void set(E e) {
      throw new IllegalModificationException();
    }


    @Contract(" -> fail")
    public void remove() {
      throw new IllegalModificationException();
    }


    @Override
    public void forEachRemaining(Consumer<? super E> action) {
      Objects.requireNonNull(action);
      if (begin) {
        action.accept(element);
        begin = false;
      }
    }
  }


  @SuppressWarnings("unchecked")
  final class ArrayIterator<E> implements ListIterator<E> {

    @NotNull
    private final Object[] array;

    private final int offset;
    private final int size;

    private int curr;


    ArrayIterator(@NotNull final Object[] array, int offset, int size) {
      this.array = array;
      this.offset = offset;
      this.size = size;
      this.curr = 0;
    }


    public ArrayIterator(@NotNull Object[] array, int offset, int size, int startFrom) {
      this.array = array;
      this.offset = offset;
      this.size = size;
      this.curr = startFrom;
    }


    @Override
    public boolean hasNext() {
      return curr < size;
    }


    @Override
    public E next() {
      if (curr < size) {
        return (E) array[offset+(curr++)];
      }
      else {
        throw new NoSuchElementException();
      }
    }


    @Override
    public boolean hasPrevious() {
      return curr > 0;
    }


    @Override
    public E previous() {
      if (curr > 0) {
        return (E) array[offset+(--curr)];
      }
      else {
        throw new NoSuchElementException();
      }
    }


    @Override
    public int nextIndex() {
      return curr;
    }


    @Override
    public int previousIndex() {
      return curr-1;
    }


    @Contract("_ -> fail")
    public void add(E e) {
      throw new IllegalModificationException();
    }


    @Contract("_ -> fail")
    public void set(E e) {
      throw new IllegalModificationException();
    }


    @Contract(" -> fail")
    public void remove() {
      throw new IllegalModificationException();
    }

  }

}
