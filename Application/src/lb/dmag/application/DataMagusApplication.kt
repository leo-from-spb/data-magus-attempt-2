package lb.dmag.application

import lb.dmag.application.settings.SettingsManipulation


/**
 * @author Leonid Bushuev
 **/
object DataMagusApplication
{

    @JvmStatic
    fun startUp()
    {
        SettingsManipulation.loadUserSettings()
    }

    @JvmStatic
    fun shutDown()
    {
        SettingsManipulation.saveUserSettings()
    }

}