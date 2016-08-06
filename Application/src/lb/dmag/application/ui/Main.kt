package lb.dmag.application.ui

import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.MenuBar
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.scene.text.Text
import javafx.stage.Stage
import lb.dmag.Log


/**
 * Main UI components.
 * @author Leonid Bushuev
 */
@SuppressWarnings("FieldCanBeLocal")
object Main
{
    private val primaryStage: Stage
    private val mainMenuBar: MenuBar
    private val rootScene: Scene

    private val log = Log.JFX

    init
    {
        log.trace("Main.Init constructor")
        assert(DataMagus.primaryStage != null)

        primaryStage = DataMagus.primaryStage!!
        mainMenuBar = MenuBar()
        mainMenuBar.useSystemMenuBarProperty().set(true)

        val btn = Button()
        btn.text = "Just a button"
        btn.setOnAction { onClick(it) }

        val stackPane = StackPane()
        stackPane.children.add(btn)

        val borderPane = BorderPane()
        borderPane.center = stackPane
        borderPane.top = Text("Top")
        borderPane.bottom = Text("Bottom")

        rootScene = Scene(borderPane, 300.0, 250.0)

        primaryStage.title = "DataMagus"
        primaryStage.scene = rootScene
    }


    fun onClick(e: ActionEvent) {
        log.info("Click!")
    }


    @JvmStatic
    fun init() {
      log.trace("Main.init() method")
    }

}
