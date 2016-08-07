package lb.dmag.application.settings

/**
 * @author Leonid Bushuev
 **/
interface AbstractSettings
{
    fun export(properties: MutableMap<String,String>)

    fun import(properties: Map<String,String>)
}