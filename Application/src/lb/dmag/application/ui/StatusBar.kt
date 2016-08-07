package lb.dmag.application.ui

import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.control.SkinBase
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox

/**
 * @author Leonid Bushuev
 **/
class StatusBar : Control
{

    private val borderPane = BorderPane()
    private val leftRow    = HBox()
    private val rightRow   = HBox()

    val left: ObservableList<Node> get() = leftRow.children
    val right: ObservableList<Node> get() = rightRow.children



    constructor() : super()

    init
    {
        borderPane.left = leftRow
        borderPane.right = rightRow

        setupRow(leftRow)
        setupRow(rightRow)

        children.add(borderPane)
    }

    private fun setupRow(row: HBox)
    {
        row.spacing = 7.0
    }

    override fun createDefaultSkin(): Skin<*> = TheSkin()

    private inner class TheSkin : SkinBase<StatusBar>(this@StatusBar)
    {

    }
}