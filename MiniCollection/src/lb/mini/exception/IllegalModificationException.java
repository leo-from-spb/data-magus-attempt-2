package lb.mini.exception;

/**
 * Thrown when attempted to modify a constant collection.
 *
 * @author Leonid Bushuev
 **/
public class IllegalModificationException extends UnsupportedOperationException {

  public IllegalModificationException() {
    super("Attempted to modify a constant collection");
  }

}
