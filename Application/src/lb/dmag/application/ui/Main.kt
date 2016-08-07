package lb.dmag.application.ui

import DataMagus
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.MenuBar
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.scene.text.Text
import javafx.stage.Stage
import lb.dmag.Log
import lb.dmag.application.settings.UserWorkspace


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

    private val log = Log.Jfx

    init
    {
        log.trace("Main.Init constructor")
        assert(DataMagus.primaryStage != null)

        primaryStage = DataMagus.primaryStage!!
        primaryStage.title = "DataMagus"

        primaryStage.x

        primaryPane.bottom = statusBar

        rootScene = Scene(primaryPane)
        primaryStage.scene = rootScene
        primaryStage.minWidth = 400.0
        primaryStage.minHeight = 400.0

        val place = UserWorkspace.windowPlace
        if (place != null) {
            primaryStage.location = place
        }
        else {
            primaryStage.centerOnScreen()
        }


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

        primaryStage.onCloseRequest = EventHandler { saveSettingsOnClose() }
    }


    private fun saveSettingsOnClose()
    {
        UserWorkspace.windowPlace = primaryStage.location
    }


    fun onClick(e: ActionEvent)
    {
        log.info("Click!")
    }


    @JvmStatic
    fun init()
    {
      log.trace("Main.init() method")
    }

}
