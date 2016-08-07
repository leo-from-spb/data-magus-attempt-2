package lb.dmag.application.settings

import javafx.geometry.Rectangle2D
import lb.dmag.application.ui.export
import lb.dmag.application.ui.importRectangle2D
import lb.dmag.util.assign

/**
 * @author Leonid Bushuev
 **/
object UserWorkspace : AbstractSettings
{
    var windowPlace:   Rectangle2D? = null


    override fun export(properties: MutableMap<String,String>)
    {
        properties.assign("Window.Place", windowPlace?.export())
    }


    override fun import(properties: Map<String,String>)
    {
        val wp = properties["Window.Place"]
        windowPlace = if (wp != null) importRectangle2D(wp) else null
    }
}