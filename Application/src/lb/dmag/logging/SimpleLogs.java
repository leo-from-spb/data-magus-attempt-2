package lb.dmag.logging;

import org.jetbrains.annotations.NotNull;



/**
 * @author Leonid Bushuev
 **/
public final class SimpleLogs {


  private LogLevel myLevel = LogLevel.Trace;

  private boolean myDebug = true;

  private int mySystemErrorThreshold = LogLevel.Error.ordinal();


  @NotNull
  public Loggy getLoggy(String name) {
    return new SystemOutputLoggy(name);
  }




  private class SystemOutputLoggy implements Loggy {

    private final String name;


    private SystemOutputLoggy(String name) {
      this.name = name;
    }


    @Override
    public LogLevel getLevel() {
      return myLevel;
    }


    @Override
    public boolean isDebug() {
      return myDebug;
    }


    @Override
    public void sendMessage(LogLevel level, String text) {
      String outText = level.name() + '\t' + name + '\t' + text.replace("\n", "\t\t\n");
      if (level.ordinal() <= mySystemErrorThreshold) {
        System.err.println(outText);
        System.err.flush();
      }
      else {
        System.out.println(outText);
        System.out.flush();
      }
    }
  }



}
