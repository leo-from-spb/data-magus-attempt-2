package lb.dmag.logging;

import java.io.PrintWriter;
import java.io.StringWriter;



/**
 * @author Leonid Bushuev
 **/
interface LogInternalUtil {

  static CharSequence handleException(Throwable exception, boolean debug) {
    if (debug) {
      StringWriter sw = new StringWriter(4096);
      PrintWriter pw = new PrintWriter(sw);
      exception.printStackTrace(pw);
      pw.flush();
      return sw.getBuffer();
    }
    else {
      return exception.getMessage();
    }
  }

}
