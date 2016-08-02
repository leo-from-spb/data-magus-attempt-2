package lb.dmag.logging;

/**
 * @author Leonid Bushuev
 **/
public abstract class Log {

  private static final SimpleLogs sl = new SimpleLogs();


  public static final Loggy JFX = sl.getLoggy("JFX");





}
