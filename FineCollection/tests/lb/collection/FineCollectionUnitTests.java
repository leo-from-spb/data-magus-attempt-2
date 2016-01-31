package lb.collection;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



/**
 * All Fine Collection unit tests.
 * @author Leonid Bushuev
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
  ConstEmptyListTest.class,
  ConstRegularListTest.class,
  ConstTest.class
})
public class FineCollectionUnitTests {}
