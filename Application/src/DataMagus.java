import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



/**
 * @author Leonid Bushuev
 **/
public final class DataMagus extends Application
{

  public static void main(String[] args) {
    Application.launch(args);
  }


  @Override
  public void init() throws Exception {
    super.init();
  }


  @Override
  public void start(Stage primaryStage) throws Exception {
    MenuBar menuBar = new MenuBar();
    menuBar.useSystemMenuBarProperty().set(true);

    Button btn = new Button();
    btn.setText("Just a button");

    StackPane root = new StackPane();
    root.getChildren().add(btn);

    Scene scene = new Scene(root, 300, 250);

    primaryStage.setScene(new Scene(new Pane(menuBar)));
    primaryStage.setTitle("Hello World!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  @Override
  public void stop() throws Exception {
    super.stop();
  }

}
