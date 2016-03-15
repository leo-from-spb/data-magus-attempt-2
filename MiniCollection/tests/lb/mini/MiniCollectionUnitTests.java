package lb.mini;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



/**
 * All Fine Collection unit tests.
 * @author Leonid Bushuev
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
  MiniEmptyTest.class,
  MiniSingletonTest.class,
  MiniRegularListTest.class,
  MiniTest.class,
  MiniKoTest.class
})
public class MiniCollectionUnitTests {}
