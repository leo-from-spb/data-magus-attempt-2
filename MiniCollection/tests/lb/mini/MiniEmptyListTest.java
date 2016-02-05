package lb.mini;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class MiniEmptyListTest {

  private final MiniList<Object> empty = MiniEmptyList.one();


  @Test
  public void is_empty() {
    assertThat(empty).isEmpty();

    assertThat(empty.isEmpty()).isTrue();
    assertThat(empty.isNotEmpty()).isFalse();
    assertThat(empty.size()).isZero();
  }


}