package lb.dmag.application.ui

import javafx.geometry.Rectangle2D
import javafx.stage.Window
import lb.dmag.util.divide
import lb.dmag.util.divideTrim


var Window.location: Rectangle2D
   get() = Rectangle2D(this.x, this.y, this.width, this.height)
   set(r)
   {
       this.x = r.minX
       this.y = r.minY
       this.width = r.width
       this.height = r.height
   }


fun Rectangle2D.export() =
    """$minX,$minY..$maxX,$maxY"""

fun importRectangle2D(str: String): Rectangle2D
{
    fun parse(s: String, part: String): Double {
        try {
            return java.lang.Double.parseDouble(s)
        }
        catch (e: NumberFormatException) {
            throw NumberFormatException("Failed to parse the $part part of Rectangle2D from string: $str")
        }
    }

    val corners = str divideTrim  ".."
    val corner1 = corners.a divideTrim  ','
    val corner2 = corners.b divideTrim  ','
    val x1 = parse(corner1.a, "MinX")
    val y1 = parse(corner1.b, "MinY")
    val x2 = parse(corner2.a, "MaxX")
    val y2 = parse(corner2.b, "MaxY")

    return Rectangle2D(x1, y1, x2-x1, y2-y1)
}