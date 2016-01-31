package lb.collection;

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
public class ConstBuilder<E> {

  @NotNull
  private final ArrayList container;



  public ConstBuilder() {
    container = new ArrayList<>();
  }


  public ConstBuilder(int initialCapacity) {
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
  public ConstList<E> buildList() {
    int size = container.size();
    if (size == 0) return ConstEmptyList.one();
    if (size == 1) return new ConstSingletonList<>((E)container.get(0));

    Object[] array = container.toArray();
    ConstRegularList<E> list = new ConstRegularList<>(array, 0, size, false);
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
