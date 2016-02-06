package lb.mini;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

import static lb.mini.Mini.*;



/**
 * @author Leonid Bushuev
 **/
@SuppressWarnings("unchecked")
abstract class MiniArraySet<E> extends MiniSet<E>  {

  //// STATE \\\\

  /**
   * Array with elements.
   */
  @NotNull
  private final Object[] array;

  /**
   * The size of THIS list.
   * Non-zero.
   */
  private final int size;



  //// CONSTRUCTOR \\\\


  MiniArraySet(@NotNull final Object[] array, int offset, int size, boolean copy) {
    copy = copy || offset != 0;
    this.array = copy ? MiniInternals.copyArraySuggestingCapacity(array, offset, size) : array;
    this.size = size;
  }


  @NotNull
  @Override
  Object[] array() {
    return array;
  }



  //// METHODS \\\\

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
    else throw new IndexOutOfBoundsException("Attempted to access element "+index+" when the set has only "+size+" elements");
  }


  @NotNull
  @Override
  public Couple<? extends MiniSet<E>> splitAt(int position) {
    final int n = size();
    if (position == 0) return Couple.of(MiniEmpty.one(), this);
    if (position == n) return Couple.of(this, MiniEmpty.one());
    if (position < 0 || position > n) throw new IndexOutOfBoundsException("Attempted to split a list of "+n+" elements at position " + position);

    final Object[] array = array();
    MiniSet<E> a = setOf(array, 0, position, false, false);
    MiniSet<E> b = setOf(array, position, n, false, false);
    return Couple.of(a, b);
  }


  @NotNull
  @Override
  public Couple<? extends MiniSet<E>> splitWhen(@NotNull Predicate<E> predicate) {
    // TODO implement MiniArraySet.splitWhen()
    throw new RuntimeException("Method MiniArraySet.splitWhen() is not implemented yet.");
  }


  @NotNull
  @Override
  public MiniSet<E> except(@Nullable Object element) {
    // TODO implement MiniArraySet.except()
    throw new RuntimeException("Method MiniArraySet.except() is not implemented yet.");
  }
}
