package lb.mini;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class MiniEmptyTest {

  private final MiniList<Object> empty = MiniEmpty.one();


  @Test
  public void is_empty() {
    assertThat(empty).isEmpty();

    assertThat(empty.isEmpty()).isTrue();
    assertThat(empty.isNotEmpty()).isFalse();
    assertThat(empty.size()).isZero();
  }


}