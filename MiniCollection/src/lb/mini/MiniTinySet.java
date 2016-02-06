package lb.mini;

import org.jetbrains.annotations.NotNull;



/**
 * @author Leonid Bushuev
 **/
final class MiniTinySet<E> extends MiniArraySet<E> {


  MiniTinySet(@NotNull Object[] array, int offset, int size, boolean copy) {
    super(array, offset, size, copy);
  }



}
