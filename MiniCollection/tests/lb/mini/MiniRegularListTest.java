package lb.mini;

import org.junit.Test;

import static lb.mini.Mini.listOf;
import static org.assertj.core.api.Assertions.assertThat;



public class MiniRegularListTest {

  @Test
  public void grow_correctness() {
    MiniRegularList<String> x = new MiniRegularList<>(new Object[] {"A","B"}, 0, 2, true);

    MiniList<String> y = x.join("Y");
    MiniList<String> z = x.join("Z");

    assertThat(x).containsExactly("A","B");
    assertThat(y).containsExactly("A","B","Y");
    assertThat(z).containsExactly("A","B","Z");

    assertThat(x.size()).isEqualTo(2);
    assertThat(y.size()).isEqualTo(3);
    assertThat(z.size()).isEqualTo(3);
  }

  @Test
  public void grow_capture_array() {
    MiniRegularList<String> x = new MiniRegularList<>(new Object[] {"A","B"}, 0, 2, true);

    MiniList<String> y = x.join("Y");
    MiniList<String> z = x.join("Z");

    assertThat(y.array()).isSameAs(x.array());
    assertThat(z.array()).isNotSameAs(x.array());
  }


  @Test
  public void splitAt_basic() {
    MiniList<String> list = listOf("A", "B", "C");

    Couple<? extends MiniList<String>> split0 = list.splitAt(0);
    assertThat(split0.a).isEmpty();
    assertThat(split0.b).isSameAs(list);

    Couple<? extends MiniList<String>> split1 = list.splitAt(1);
    assertThat(split1.a).containsExactly("A");
    assertThat(split1.b).containsExactly("B","C");

    Couple<? extends MiniList<String>> split2 = list.splitAt(2);
    assertThat(split2.a).containsExactly("A","B");
    assertThat(split2.b).containsExactly("C");

    Couple<? extends MiniList<String>> split3 = list.splitAt(3);
    assertThat(split3.a).isSameAs(list);
    assertThat(split3.b).isEmpty();
  }


  @Test
  public void splitWhen_odd() {
    MiniList<Long> numbers = listOf(1L, 2L, 3L, 4L, 5L, 6L);
    Couple<? extends MiniList<Long>> split = numbers.splitWhen(number -> number % 2 == 1);
    assertThat(split.a).contains(1L, 3L, 5L);
    assertThat(split.b).contains(2L, 4L, 6L);
  }

  @Test
  public void splitWhen_trivial() {
    MiniList<Long> numbers = listOf(1L, 2L, 3L);

    Couple<? extends MiniList<Long>> split1 = numbers.splitWhen(number -> number > 100L);
    assertThat(split1.a).isEmpty();
    assertThat(split1.b).isSameAs(numbers);

    Couple<? extends MiniList<Long>> split2 = numbers.splitWhen(number -> number < 100L);
    assertThat(split2.a).isSameAs(numbers);
    assertThat(split2.b).isEmpty();
  }


  @Test
  public void except_1() {
    MiniList<Long> list = listOf(1L, 2L, 3L, 4L, 5L);

    assertThat(list.except(1L)).containsExactly(2L, 3L, 4L, 5L);
    assertThat(list.except(3L)).containsExactly(1L, 2L, 4L, 5L);
    assertThat(list.except(5L)).containsExactly(1L, 2L, 3L, 4L);
  }

  @Test
  public void except_2() {
    MiniList<Long> list = listOf(111L, 99L, 333L, 99L, 555L);

    assertThat(list.except(99L)).containsExactly(111L, 333L, 555L);
  }

}