package lb.mini;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;



/**
 * @author Leonid Bushuev
 **/
final class MiniSingleton<E> extends MiniSet<E> {

  //// THE STATE \\\\

  @NotNull
  private final E element;


  //// CONSTRUCTOR \\\\

  MiniSingleton(@NotNull final E element) {
    this.element = element;
  }


  @NotNull
  @Override
  Object[] array() {
    throw new IllegalStateException("Internal error. Nobody should call MiniSingleton.array().");
  }


  //// MANIPULATIONS \\\\

  @NotNull
  @Override
  public MiniList<E> join(@NotNull E element) {
    Object[] array = new Object[] {this.element, element, null, null};
    return new MiniRegularList<>(array, 0, 2, false);
  }


  @NotNull
  @Override
  public Couple<MiniSet<E>> splitAt(int position) {
    switch (position) {
      case 0: return Couple.of(MiniEmpty.one(), this);
      case 1: return Couple.of(this, MiniEmpty.one());
      default: throw new IndexOutOfBoundsException("Attempted to split a list of one element at position " + position);
    }
  }


  @NotNull
  @Override
  public Couple<MiniSet<E>> splitWhen(@NotNull Predicate<E> predicate) {
    if (predicate.test(element))  return Couple.of(this, MiniEmpty.one());
    else                          return Couple.of(MiniEmpty.one(), this);
  }


  @NotNull
  @Override
  public MiniSet<E> except(@Nullable Object element) {
    if (this.element.equals(element))  return MiniEmpty.one();
    else                               return this;
  }


  //// FUNCTIONS \\\\


  @NotNull
  @Override
  public E getFirst() {
    return element;
  }


  @NotNull
  @Override
  public E getLast() {
    return element;
  }


  @NotNull
  @Override
  public E get(int index) {
    if (index == 0) return element;
    else throw new IndexOutOfBoundsException("The only possible index is 0 when got " + index);
  }


  @Override
  public int size() {
    return 1;
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
  public boolean contains(Object o) {
    return element.equals(o);
  }


  @Override
  public int indexOf(Object o) {
    return element.equals(o) ? 0 : -1;
  }


  @Override
  public int lastIndexOf(Object o) {
    return element.equals(o) ? 0 : -1;
  }


  @NotNull
  @Override
  public Iterator<E> iterator() {
    return new MiniInternals.SingletonIterator<E>(element);
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator() {
    return new MiniInternals.SingletonIterator<E>(element);
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator(int index) {
    switch (index) {
      case 0: return new MiniInternals.SingletonIterator<E>(element);
      case 1: return new MiniInternals.SingletonIterator<E>(element, false);
      default: throw new IndexOutOfBoundsException("The constant list contains only one element.");
    }
  }


  @Override
  public Spliterator<E> spliterator() {
    return Collections.singleton(element).spliterator();
  }


  @NotNull
  @Override
  public MiniSet<E> subList(int fromIndex, int toIndex) {
    if (fromIndex == 0 && toIndex == 1) return this;
    if (fromIndex == toIndex) return MiniEmpty.one();
    throw new IndexOutOfBoundsException("The constant list contains only one element.");
  }


  @NotNull
  @Override
  public Object[] toArray() {
    return new Object[] {element};
  }


  @NotNull
  @Override
  public <T> T[] toArray(@NotNull T[] a) {
    int capacity = a.length;
    switch (capacity) {
      case 0:
        final Class<?> componentType = a.getClass().getComponentType();
        //noinspection unchecked
        T[] newArray = (T[]) Array.newInstance(componentType, 1);
        Array.set(newArray, 0, element);
        return newArray;
      case 1:
        Array.set(a, 0, element);
        return a;
      default:
        Array.set(a, 0, element);
        Arrays.fill(a, 1, a.length, null);
        return a;
    }
  }


}
