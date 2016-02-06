package lb.mini;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;



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
  private final Object[] array;

  /**
   * The size of THIS list.
   * Non-zero.
   */
  private final int size;



  //// CONSTRUCTOR \\\\


  MiniRegularList(@NotNull final Object[] array, int offset, int size, boolean copy) {
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
    else throw new IndexOutOfBoundsException("Attempted to access element "+index+" when the list has only "+size+" elements");
  }


}
