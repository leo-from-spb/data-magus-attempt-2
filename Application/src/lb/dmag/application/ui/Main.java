package lb.dmag.application.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lb.dmag.logging.Log;
import lb.dmag.logging.Loggy;

import java.util.logging.Logger;



/**
 * @author Leonid Bushuev
 **/
@SuppressWarnings("FieldCanBeLocal")
public class Main {

  public static final Loggy log = Log.JFX;

  private final Stage     myPrimaryStage;
  private final MenuBar   myMainMenuBar;
  private final Scene     myRootScene;


  public Main(Stage primaryStage) {
    myPrimaryStage = primaryStage;
    myMainMenuBar = new MenuBar();
    myMainMenuBar.useSystemMenuBarProperty().set(true);


    Button btn = new Button();
    btn.setText("Just a button");
    btn.setOnAction(e -> log.info("Click!"));

    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(btn);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(stackPane);
    borderPane.setTop(new Text("Top"));
    borderPane.setBottom(new Text("Bottom"));

    myRootScene = new Scene(borderPane, 300, 250);

    myPrimaryStage.setTitle("DataMagus");
    myPrimaryStage.setScene(myRootScene);
  }


}
