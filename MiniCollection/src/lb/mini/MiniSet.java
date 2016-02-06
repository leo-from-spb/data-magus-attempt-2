package lb.mini;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;



/**
 * @author Leonid Bushuev
 **/
public abstract class MiniSet<E> extends MiniList<E> implements Set<E> {

  @NotNull
  @Override
  public abstract Couple<? extends MiniSet<E>> splitAt(int position);


  @NotNull
  @Override
  public abstract Couple<? extends MiniSet<E>> splitWhen(@NotNull Predicate<E> predicate);


  @NotNull
  @Override
  public abstract MiniSet<E> except(@Nullable Object element);


  //// DEFAULT IMPLEMENTATION \\\\

  @Override
  public Spliterator<E> spliterator() {
    return Spliterators.spliterator(this, Spliterator.SIZED | Spliterator.NONNULL | Spliterator.ORDERED | Spliterator.DISTINCT | Spliterator.IMMUTABLE);
  }



}
