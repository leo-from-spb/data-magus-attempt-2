package lb.mini;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * Constant collections builder.
 *
 * @author Leonid Bushuev
 *
 * // TODO optimize
 **/
@SuppressWarnings("unchecked")
public class MiniBuilder<E> {

  @NotNull
  private final ArrayList container;



  public MiniBuilder() {
    container = new ArrayList<>();
  }


  public MiniBuilder(int initialCapacity) {
    container = new ArrayList<>(initialCapacity);
  }


  public void add(@NotNull final E element) {
    container.add(element);
  }

  void addAll(@NotNull final Object[] elements, int from, int till, boolean checkNulls) {
    if (checkNulls) {
      for (int i = from; i < till; i++) {
        assert elements[i] != null : String.format("ConstBuilder.addAll(elements from %d till %d): the %d'th element is null",
                                                   from, till, i);
      }
    }
    container.addAll(Arrays.asList(elements).subList(from,till)); // TODO optimize
  }


  @NotNull
  public MiniList<E> buildList() {
    int size = container.size();
    if (size == 0) return MiniEmptyList.one();
    if (size == 1) return new MiniSingletonList<>((E)container.get(0));

    Object[] array = container.toArray();
    MiniRegularList<E> list = new MiniRegularList<>(array, 0, size, false);
    container.clear();

    return list;
  }


  public boolean isEmpty() {
    return container.isEmpty();
  }

  public boolean isNotEmpty() {
    return !container.isEmpty();
  }


}
