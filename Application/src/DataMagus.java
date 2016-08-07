import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import lb.dmag.application.DataMagusApplication;
import lb.dmag.application.ui.Main;
import lb.dmag.Log;
import lb.dmag.logging.Loggy;



/**
 * @author Leonid Bushuev
 **/
public final class DataMagus extends Application
{

  private static final Loggy log = Log.INSTANCE.getJfx();


  public static Stage primaryStage = null;


  public static void main(String[] args) {
    Thread.currentThread().setName("DataMagus-Start");
    log.trace("Application is starting up");
    DataMagusApplication.startUp();
    LauncherImpl.launchApplication(DataMagus.class, null, args);
    DataMagusApplication.shutDown();
    log.trace("Application shut down");
  }


  @Override
  public void init() throws Exception {
    log.trace("JFX is initializing in thread %s [%d]", Thread.currentThread().getName(), Thread.currentThread().getId());
    super.init();
  }


  @Override
  public void start(Stage primaryStage) throws Exception {
    DataMagus.primaryStage = primaryStage;
    log.trace("JFX is starting in thread %s [%d]", Thread.currentThread().getName(), Thread.currentThread().getId());
    Main.init();
    primaryStage.show();
  }


  @Override
  public void stop() throws Exception {
    log.trace("JFX is stopping in thread %s [%d]", Thread.currentThread().getName(), Thread.currentThread().getId());
    super.stop();
  }

}
