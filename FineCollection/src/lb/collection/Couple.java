package lb.collection;

import org.jetbrains.annotations.NotNull;



/**
 * A couple of items, both items are of the same type and both of them are not null.
 * @author Leonid Bushuev
 **/
public final class Couple<E> {

  //// STATE \\\\

  @NotNull
  public final E a;

  @NotNull
  public final E b;


  //// CONSTRUCTOR \\\\


  public static <T> Couple<T> of(@NotNull final T a, @NotNull final T b) {
    return new Couple<>(a, b);
  }


  private Couple(@NotNull final E a, @NotNull final E b) {
    this.a = a;
    this.b = b;
  }


  //// LEGACY METHODS \\\\

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Couple<?> that = (Couple<?>)o;
    return this.a.equals(that.a) && this.b.equals(that.b);
  }


  @Override
  public int hashCode() {
    return a.hashCode() * 13 + b.hashCode() * 11;
  }


  @NotNull
  public ConstList<E> toList() {
    return Const.listOf(a, b);
  }

}
