package lb.collection;

import org.junit.Test;

import static lb.collection.Const.emptyList;
import static lb.collection.Const.listOf;
import static org.assertj.core.api.Assertions.assertThat;



public class ConstTest {

  @Test
  public void listOf_size() {
    assertThat(listOf().size()).isZero();
    assertThat(listOf("A").size()).isEqualTo(1);
    assertThat(listOf("A","B").size()).isEqualTo(2);
    assertThat(listOf("A","B","C").size()).isEqualTo(3);
    assertThat(listOf("A","B","C","D").size()).isEqualTo(4);
    assertThat(listOf("A","B","C","D","E").size()).isEqualTo(5);
    assertThat(listOf("A","B","C","D","E","F").size()).isEqualTo(6);
  }

  @Test
  public void listOf_content() {
    assertThat(listOf()).isEmpty();
    assertThat(listOf("A")).containsExactly("A");
    assertThat(listOf("A","B")).containsExactly("A","B");
    assertThat(listOf("A","B","C")).containsExactly("A","B","C");
    assertThat(listOf("A","B","C","D")).containsExactly("A","B","C","D");
    assertThat(listOf("A","B","C","D","E")).containsExactly("A","B","C","D","E");
    assertThat(listOf("A","B","C","D","E","F")).containsExactly("A","B","C","D","E","F");
  }

  @Test
  public void listOf_first() {
    assertThat(listOf("A").getFirst()).isEqualTo("A");
    assertThat(listOf("A","B").getFirst()).isEqualTo("A");
    assertThat(listOf("A","B","C").getFirst()).isEqualTo("A");
    assertThat(listOf("A","B","C","D").getFirst()).isEqualTo("A");
    assertThat(listOf("A","B","C","D","E").getFirst()).isEqualTo("A");
    assertThat(listOf("A","B","C","D","E","F").getFirst()).isEqualTo("A");
  }

  @Test
  public void listOf_last() {
    assertThat(listOf("A").getLast()).isEqualTo("A");
    assertThat(listOf("A","B").getLast()).isEqualTo("B");
    assertThat(listOf("A","B","C").getLast()).isEqualTo("C");
    assertThat(listOf("A","B","C","D").getLast()).isEqualTo("D");
    assertThat(listOf("A","B","C","D","E").getLast()).isEqualTo("E");
    assertThat(listOf("A","B","C","D","E","F").getLast()).isEqualTo("F");
  }

  @Test
  public void listOf_element_0() {
    assertThat(listOf("A").get(0)).isEqualTo("A");
    assertThat(listOf("A","B").get(0)).isEqualTo("A");
    assertThat(listOf("A","B","C").get(0)).isEqualTo("A");
    assertThat(listOf("A","B","C","D").get(0)).isEqualTo("A");
    assertThat(listOf("A","B","C","D","E").get(0)).isEqualTo("A");
    assertThat(listOf("A","B","C","D","E","F").get(0)).isEqualTo("A");
  }

  @Test
  public void listOf_element_n() {
    assertThat(listOf("A").get(0)).isEqualTo("A");
    assertThat(listOf("A","B").get(1)).isEqualTo("B");
    assertThat(listOf("A","B","C").get(2)).isEqualTo("C");
    assertThat(listOf("A","B","C","D").get(3)).isEqualTo("D");
    assertThat(listOf("A","B","C","D","E").get(4)).isEqualTo("E");
    assertThat(listOf("A","B","C","D","E","F").get(5)).isEqualTo("F");
  }


  @Test
  public void listOf_contains() {
    assertThat(listOf("A").contains("A")).isTrue();
    assertThat(listOf("A","B").contains("A")).isTrue();
    assertThat(listOf("A","B").contains("B")).isTrue();
    assertThat(listOf("A","B","C").contains("A")).isTrue();
    assertThat(listOf("A","B","C").contains("B")).isTrue();
    assertThat(listOf("A","B","C").contains("C")).isTrue();
    assertThat(listOf("A","B","C","D").contains("A")).isTrue();
    assertThat(listOf("A","B","C","D").contains("B")).isTrue();
    assertThat(listOf("A","B","C","D").contains("C")).isTrue();
    assertThat(listOf("A","B","C","D").contains("D")).isTrue();
  }


  @Test
  public void grow_sequentially() {
    ConstList<String> x = emptyList();

    x = x.grow("A"); assertThat(x).containsExactly("A");
    x = x.grow("B"); assertThat(x).containsExactly("A","B");
    x = x.grow("C"); assertThat(x).containsExactly("A","B","C");
    x = x.grow("D"); assertThat(x).containsExactly("A","B","C","D");
    x = x.grow("E"); assertThat(x).containsExactly("A","B","C","D","E");
    x = x.grow("F"); assertThat(x).containsExactly("A","B","C","D","E","F");
    x = x.grow("G"); assertThat(x).containsExactly("A","B","C","D","E","F","G");
    x = x.grow("H"); assertThat(x).containsExactly("A","B","C","D","E","F","G","H");
    x = x.grow("I"); assertThat(x).containsExactly("A","B","C","D","E","F","G","H","I");
    x = x.grow("J"); assertThat(x).containsExactly("A","B","C","D","E","F","G","H","I","J");
  }


  @Test
  public void except_null() {
    ConstList<Object> list0 = emptyList();
    assertThat(list0.except(null)).isSameAs(list0);

    ConstList<Object> list1 = listOf(1111L);
    assertThat(list1.except(null)).isSameAs(list1);

    ConstList<Object> list2 = listOf(1111L,2222L);
    assertThat(list2.except(null)).isSameAs(list2);
  }

  @Test
  public void except_unexistent() {
    ConstList<Object> list0 = emptyList();
    assertThat(list0.except(-1234L)).isSameAs(list0);

    ConstList<Object> list1 = listOf(1111L);
    assertThat(list1.except(1234L)).isSameAs(list1);

    ConstList<Object> list2 = listOf(1111L,2222L);
    assertThat(list2.except(1234L)).isSameAs(list2);
  }


}