package lb.dmag.application.ui

import DataMagus
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
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
    private val rootScene: Scene

    private val mainMenuBar = MenuBar()
    private val primaryPane = BorderPane()
    private val statusBar = StatusBar()

    private val log = Log.JFX

    init
    {
        log.trace("Main.Init constructor")
        assert(DataMagus.primaryStage != null)

        primaryStage = DataMagus.primaryStage!!
        primaryStage.title = "DataMagus"

        primaryPane.bottom = statusBar

        rootScene = Scene(primaryPane, 300.0, 250.0)
        primaryStage.scene = rootScene

        mainMenuBar.useSystemMenuBarProperty().set(true)

        statusBar.left.add(Text("Text1"))
        statusBar.left.add(Text("Text2"))
        statusBar.left.add(Text("Text3"))

        statusBar.right.add(Text("Scale"))
        statusBar.right.add(Label("zzz"))

        val btn = Button()
        btn.text = "Just a button"
        btn.setOnAction { onClick(it) }

        val stackPane = StackPane()
        stackPane.children.add(btn)

        primaryPane.center = stackPane
        primaryPane.top = Text("Top")
        primaryPane.bottom = statusBar

    }


    fun onClick(e: ActionEvent) {
        log.info("Click!")
    }


    @JvmStatic
    fun init() {
      log.trace("Main.init() method")
    }

}
