package lb.collection;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;



/**
 * @author Leonid Bushuev
 **/
public abstract class ConstList<E> extends ConstCollection<E> implements List<E>, RandomAccess {

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


  @Override
  public abstract int indexOf(Object o);


  @Override
  public abstract int lastIndexOf(Object o);



  //// MANIPULATIONS \\\\


  /**
   * Creates a new list that contains all items from this one
   * plus one more <tt>element</tt>.
   * @param element the element to add to the list.
   * @return        the result list.
   */
  public abstract ConstList<E> grow(@NotNull final E element);

  /**
   * Splits the list at the specified position.
   * @param position  the position for splitting.
   * @return          a couple of lists.
   */
  @NotNull
  public abstract Couple<ConstList<E>> splitAt(int position);

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
  public abstract Couple<ConstList<E>> splitWhen(@NotNull Predicate<E> predicate);

  /**
   * Returns the list without the given element.
   * @param element  undesired element.
   * @return         the list that doesn't contain the given <tt>element</tt>.
   */
  @NotNull
  public abstract ConstList<E> except(@Nullable final Object element);


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
