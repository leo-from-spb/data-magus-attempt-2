package lb.dmag

import lb.dmag.logging.SimpleLogs


/**
 * @author Leonid Bushuev
 */
object Log
{
    @JvmStatic
    private val sl = SimpleLogs()


    val JFX = sl.getLoggy("JFX")


}
