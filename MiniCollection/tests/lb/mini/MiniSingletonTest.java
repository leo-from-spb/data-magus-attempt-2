package lb.mini;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class MiniSingletonTest {

  @Test
  public void toArray_untyped() {
    MiniSingleton<Long> m = new MiniSingleton<>(777L);
    Object[] array = m.toArray();
    assertThat(array).hasSize(1)
                     .containsExactly(777L);
  }

  @Test
  public void toArray_exact() {
    MiniSingleton<Long> m = new MiniSingleton<>(777L);
    Object[] array = m.toArray(new Long[1]);
    assertThat(array).hasSize(1)
                     .containsExactly(777L)
                     .isExactlyInstanceOf(Long[].class);
  }

  @Test
  public void toArray_empty() {
    MiniSingleton<Long> m = new MiniSingleton<>(777L);
    Object[] array = m.toArray(new Long[0]);
    assertThat(array).hasSize(1)
                     .containsExactly(777L)
                     .isExactlyInstanceOf(Long[].class);
  }

  @Test
  public void toArray_too_large() {
    MiniSingleton<Long> m = new MiniSingleton<>(777L);
    Object[] array = m.toArray(new Long[3]);
    assertThat(array).hasSize(3)
                     .containsExactly(777L, null, null)
                     .isExactlyInstanceOf(Long[].class);
  }

}