package lb.collection;

import org.jetbrains.annotations.NotNull;



/**
 * @author Leonid Bushuev
 **/
public abstract class Const {


  @NotNull
  public static <E> ConstList<E> listOf(@NotNull final E element) {
    return new ConstSingletonList<>(element);
  }

  @NotNull
  public static <E> ConstList<E> listOf(@NotNull final E element1, @NotNull final E element2) {
    final Object[] array = new Object[] {element1, element2};
    return new ConstRegularList<E>(array, 0, 2, false);
  }

  @NotNull
  public static <E> ConstList<E> listOf(@NotNull final E element1, @NotNull final E element2, @NotNull final E element3) {
    final Object[] array = new Object[] {element1, element2, element3};
    return new ConstRegularList<E>(array, 0, 3, false);
  }

  @NotNull
  @SafeVarargs
  public static <E> ConstList<E> listOf(final E... elements) {
    final int n = elements.length;
    if (n == 0) return emptyList();

    for (int i = 0; i < n; i++) {
      assert elements[i] != null : "Const.listOf(" + n + " elements): the " + i + "'th element is null";
    }

    return n == 1
           ? new ConstSingletonList<>(elements[0])
           : new ConstRegularList<>(elements, 0, n, true);
  }

  @NotNull
  public static <E> ConstList<E> listOf(@NotNull final E[] elements, int from, int till) {
    return listOf(elements, from, till, true);
  }

  @NotNull
  static <E> ConstList<E> listOf(@NotNull final Object[] elements, int from, int till, boolean checkNulls) {
    int n = till - from;
    if (n == 0) return emptyList();

    if (checkNulls) {
      for (int i = from; i < till; i++) {
        assert elements[i] != null : String.format("Const.listOf(elements from %d till %d): the %d'th element is null",
                                                   from, till, i);
      }
    }

    //noinspection unchecked
    return n == 1
           ? new ConstSingletonList<>((E)elements[from])
           : new ConstRegularList<>(elements, from, n, true);
  }



  @NotNull
  public static <E> ConstList<E> emptyList() {
    return ConstEmptyList.one();
  }




}
