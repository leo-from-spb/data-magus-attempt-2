package lb.dmagus.collections;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

import static java.lang.System.arraycopy;



/**
 * @author Leonid Bushuev from JetBrains
 **/
@SuppressWarnings("unchecked")
public abstract class FrozenArrays {


  @NotNull
  static <T, E extends T> T[] newWithOne(@NotNull final Class<T> elementClass, @NotNull final E element) {
    T[] array = (T[]) Array.newInstance(elementClass, 1);
    array[0] = element;
    return array;
  }


  @NotNull
  static <T> T[] newEmptyArray(@NotNull final Class<T> elementClass) {
    return (T[]) Array.newInstance(elementClass, 0);
  }


  @NotNull
  static <T, E extends T> T[] addOne(@NotNull final T[] origArray, @NotNull final E element) {
    int n = origArray.length;
    T[] newArray = (T[]) Array.newInstance(origArray.getClass().getComponentType(), n + 1);
    arraycopy(origArray, 0, newArray, 0, n);
    newArray[n] = element;
    return newArray;
  }


  @NotNull
  static <T, E extends T> T[] removeElement(@NotNull final T[] origArray, @NotNull final E element) {
    int p = lastIndexOf(origArray, element);
    if (p >= 0) {
      return removeAt(origArray, p);
    }
    else {
      return origArray;
    }
  }

  @NotNull
  static <T> T[] removeAt(@NotNull final T[] origArray, final int index) {
    int n = origArray.length;
    T[] newArray = (T[]) Array.newInstance(origArray.getClass().getComponentType(), n - 1);
    if (index > 0) arraycopy(origArray, 0, newArray, 0, index);
    if (index < n-1) arraycopy(origArray, index+1, newArray, index, n-index-1);
    return newArray;
  }


  static <T, E extends T> int lastIndexOf(@NotNull final T[] array, @NotNull final E element) {
    for (int i = array.length-1; i >= 0; i--)
      if (element.equals(array[i]))
        return i;
    return -1;
  }

}
