package lb.collection;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Predicate;



/**
 * @author Leonid Bushuev
 **/
final class ConstSingletonList<E> extends ConstList<E> {

  //// THE STATE \\\\

  @NotNull
  private final E element;


  //// CONSTRUCTOR \\\\

  ConstSingletonList(@NotNull final E element) {
    this.element = element;
  }



  //// MANIPULATIONS \\\\

  @NotNull
  @Override
  public ConstList<E> grow(@NotNull E element) {
    Object[] array = new Object[] {this.element, element, null, null};
    return new ConstRegularList<>(array, 0, 2, false);
  }


  @NotNull
  @Override
  public Couple<ConstList<E>> splitAt(int position) {
    switch (position) {
      case 0: return Couple.of(ConstEmptyList.one(), this);
      case 1: return Couple.of(this, ConstEmptyList.one());
      default: throw new IndexOutOfBoundsException("Attempted to split a list of one element at position " + position);
    }
  }


  @NotNull
  @Override
  public Couple<ConstList<E>> splitWhen(@NotNull Predicate<E> predicate) {
    if (predicate.test(element))  return Couple.of(this, ConstEmptyList.one());
    else                          return Couple.of(ConstEmptyList.one(), this);
  }


  @NotNull
  @Override
  public ConstList<E> except(@Nullable Object element) {
    if (this.element.equals(element))  return ConstEmptyList.one();
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
    return new ConstInternals.SingletonIterator<E>(element);
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator() {
    return new ConstInternals.SingletonIterator<E>(element);
  }


  @NotNull
  @Override
  public ListIterator<E> listIterator(int index) {
    switch (index) {
      case 0: return new ConstInternals.SingletonIterator<E>(element);
      case 1: return new ConstInternals.SingletonIterator<E>(element, false);
      default: throw new IndexOutOfBoundsException("The constant list contains only one element.");
    }
  }


  @NotNull
  @Override
  public ConstList<E> subList(int fromIndex, int toIndex) {
    if (fromIndex == 0 && toIndex == 1) return this;
    if (fromIndex == toIndex) return ConstEmptyList.one();
    throw new IndexOutOfBoundsException("The constant list contains only one element.");
  }
}
