package lb.dmagus.model;

import lb.dmagus.model.concept.AttributeTest;
import lb.dmagus.model.concept.SubjectAreaTest;
import lb.dmagus.model.core.ModelTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({

  ModelTest.class,
  SubjectAreaTest.class,
  AttributeTest.class

})
public class ModelUnitTests {}
