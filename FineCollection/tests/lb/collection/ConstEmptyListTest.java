package lb.collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class ConstEmptyListTest {

  private final ConstList<Object> empty = ConstEmptyList.one();


  @Test
  public void is_empty() {
    assertThat(empty).isEmpty();

    assertThat(empty.isEmpty()).isTrue();
    assertThat(empty.isNotEmpty()).isFalse();
    assertThat(empty.size()).isZero();
  }


}