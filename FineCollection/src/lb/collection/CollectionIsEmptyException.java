package lb.collection;

/**
 * @author Leonid Bushuev
 **/
public class CollectionIsEmptyException extends RuntimeException {

  public CollectionIsEmptyException() {
    super("The collection is empty");
  }

}
