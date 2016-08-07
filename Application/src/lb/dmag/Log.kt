package lb.dmag

import lb.dmag.logging.SimpleLogs


/**
 * @author Leonid Bushuev
 */
object Log
{
    @JvmStatic
    private val sl = SimpleLogs()


    val App = sl.getLoggy("App")
    val Jfx = sl.getLoggy("Jfx")


}
