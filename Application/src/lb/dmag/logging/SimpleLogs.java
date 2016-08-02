package lb.dmag.logging;

/**
 * @author Leonid Bushuev
 **/
final class SimpleLogs {


  LogLevel myLevel = LogLevel.Trace;

  boolean myDebug = true;

  int mySystemErrorThreshold = LogLevel.Error.ordinal();


  Loggy getLoggy(String name) {
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
