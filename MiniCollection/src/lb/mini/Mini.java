package lb.mini;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static lb.mini.MiniInternals.checkNotNull;



/**
 * @author Leonid Bushuev
 **/
public abstract class Mini {


  //// LISTS \\\\

  @NotNull
  public static <E> MiniList<E> listOf(@NotNull final E element) {
    return new MiniSingleton<>(element);
  }

  @NotNull
  public static <E> MiniList<E> listOf(@NotNull final E element1, @NotNull final E element2) {
    final Object[] array = new Object[] {element1, element2};
    return new MiniRegularList<E>(array, 0, 2, false);
  }

  @NotNull
  public static <E> MiniList<E> listOf(@NotNull final E element1, @NotNull final E element2, @NotNull final E element3) {
    final Object[] array = new Object[] {element1, element2, element3};
    return new MiniRegularList<E>(array, 0, 3, false);
  }

  @NotNull
  @SafeVarargs
  public static <E> MiniList<E> listOf(@NotNull final E... elements) {
    final int n = elements.length;
    if (n == 0) return empty();

    for (int i = 0; i < n; i++) {
      assert elements[i] != null : "Const.listOf(" + n + " elements): the " + i + "'th element is null";
    }

    return n == 1
           ? new MiniSingleton<>(elements[0])
           : new MiniRegularList<>(elements, 0, n, true);
  }

  @NotNull
  public static <E> MiniList<E> listOf(@NotNull final E[] elements, int from, int till) {
    return listOf(elements, from, till, true);
  }

  @NotNull
  static <E> MiniList<E> listOf(@NotNull final Object[] elements, int from, int till, boolean checkNulls) {
    int n = till - from;
    if (n == 0) return empty();

    if (checkNulls) {
      for (int i = from; i < till; i++) {
        assert elements[i] != null : String.format("Const.listOf(elements from %d till %d): the %d'th element is null",
                                                   from, till, i);
      }
    }

    //noinspection unchecked
    return n == 1
           ? new MiniSingleton<>((E)elements[from])
           : new MiniRegularList<>(elements, from, n, true);
  }


  //// SETS \\\\

  @NotNull
  public static <E> MiniSet<E> setOf(@NotNull final E element) {
    return new MiniSingleton<>(element);
  }

  @NotNull
  public static <E> MiniSet<E> setOf(@NotNull final E element1, @NotNull final E element2) {
    return element1.equals(element2)
           ? setOf(element1)
           : new MiniTinySet<E>(new Object[]{element1, element2}, 0, 2, false);
  }

  @NotNull
  @SafeVarargs
  public static <E> MiniSet<E> setOf(@NotNull final E... elements) {
    final int n = elements.length;
    switch (n) {
      case 0: return empty();
      case 1: return setOf(checkNotNull(elements[0]));
      default: return setOf(elements, 0, n, true, true);
    }
  }

  @NotNull
  static <E> MiniSet<E> setOf(@NotNull final Object[] elements, int from, int till, boolean deduplicate, boolean checkNulls) {
    int n = till - from;
    if (n == 0) return empty();

    if (checkNulls) {
      for (int i = from; i < till; i++) {
        assert elements[i] != null : String.format("Const.listOf(elements from %d till %d): the %d'th element is null",
                                                   from, till, i);
      }
    }

    // TODO deduplicate

    //noinspection unchecked
    return n == 1
           ? new MiniSingleton<>((E)elements[from])
           : new MiniTinySet<>(elements, from, n, true);
  }


  @NotNull @Contract(pure = true)
  public static <E> MiniSet<E> empty() {
    return MiniEmpty.one();
  }

}
