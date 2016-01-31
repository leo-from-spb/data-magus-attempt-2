package lb.collection;

import org.junit.Test;

import static lb.collection.Const.listOf;
import static org.assertj.core.api.Assertions.assertThat;



public class ConstRegularListTest {

  @Test
  public void grow_correctness() {
    ConstRegularList<String> x = new ConstRegularList<>(new Object[] {"A","B"}, 0, 2, true);

    ConstRegularList<String> y = x.grow("Y");
    ConstRegularList<String> z = x.grow("Z");

    assertThat(x).containsExactly("A","B");
    assertThat(y).containsExactly("A","B","Y");
    assertThat(z).containsExactly("A","B","Z");

    assertThat(x.size()).isEqualTo(2);
    assertThat(y.size()).isEqualTo(3);
    assertThat(z.size()).isEqualTo(3);
  }

  @Test
  public void grow_capture_array() {
    ConstRegularList<String> x = new ConstRegularList<>(new Object[] {"A","B"}, 0, 2, true);

    ConstRegularList<String> y = x.grow("Y");
    ConstRegularList<String> z = x.grow("Z");

    assertThat(y.array).isSameAs(x.array);
    assertThat(z.array).isNotSameAs(x.array);
  }


  @Test
  public void splitAt_basic() {
    ConstList<String> list = listOf("A", "B", "C");

    Couple<ConstList<String>> split0 = list.splitAt(0);
    assertThat(split0.a).isEmpty();
    assertThat(split0.b).isSameAs(list);

    Couple<ConstList<String>> split1 = list.splitAt(1);
    assertThat(split1.a).containsExactly("A");
    assertThat(split1.b).containsExactly("B","C");

    Couple<ConstList<String>> split2 = list.splitAt(2);
    assertThat(split2.a).containsExactly("A","B");
    assertThat(split2.b).containsExactly("C");

    Couple<ConstList<String>> split3 = list.splitAt(3);
    assertThat(split3.a).isSameAs(list);
    assertThat(split3.b).isEmpty();
  }


  @Test
  public void splitWhen_odd() {
    ConstList<Long> numbers = listOf(1L, 2L, 3L, 4L, 5L, 6L);
    Couple<ConstList<Long>> split = numbers.splitWhen(number -> number % 2 == 1);
    assertThat(split.a).contains(1L, 3L, 5L);
    assertThat(split.b).contains(2L, 4L, 6L);
  }

  @Test
  public void splitWhen_trivial() {
    ConstList<Long> numbers = listOf(1L, 2L, 3L);

    Couple<ConstList<Long>> split1 = numbers.splitWhen(number -> number > 100L);
    assertThat(split1.a).isEmpty();
    assertThat(split1.b).isSameAs(numbers);

    Couple<ConstList<Long>> split2 = numbers.splitWhen(number -> number < 100L);
    assertThat(split2.a).isSameAs(numbers);
    assertThat(split2.b).isEmpty();
  }


  @Test
  public void except_1() {
    ConstList<Long> list = listOf(1L, 2L, 3L, 4L, 5L);

    assertThat(list.except(1L)).containsExactly(2L, 3L, 4L, 5L);
    assertThat(list.except(3L)).containsExactly(1L, 2L, 4L, 5L);
    assertThat(list.except(5L)).containsExactly(1L, 2L, 3L, 4L);
  }

  @Test
  public void except_2() {
    ConstList<Long> list = listOf(111L, 99L, 333L, 99L, 555L);

    assertThat(list.except(99L)).containsExactly(111L, 333L, 555L);
  }

}