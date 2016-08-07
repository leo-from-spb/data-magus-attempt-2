package lb.dmag.application.settings

import lb.dmag.Log
import lb.dmag.util.divideTrim
import java.io.File
import java.io.IOException
import java.util.*


object SettingsManipulation
{
    const val applicationSettingsFolderName = ".DataMagus"
    const val userWorkspaceFileName = "workspace.properties"

    val userHomeDir: File
    val applicationSettingsDir: File

    init
    {
        userHomeDir = File(System.getProperty("user.home"))
        applicationSettingsDir = File(userHomeDir, applicationSettingsFolderName)
        if (!applicationSettingsDir.exists()) applicationSettingsDir.mkdir()
    }



    fun saveSetting(settings: AbstractSettings, file: File)
    {
        val map = LinkedHashMap<String, String>()
        settings.export(map)

        if (map.isEmpty()) return

        val b = StringBuilder()
        for ((key, value) in map)
            b.append(key).append(" = ").append(value).append('\n')

        try
        {
            file.parentFile.mkdirs()
            file.writeText(b.toString())
        }
        catch(ё: IOException)
        {
            Log.App.error(ё, "Failed to save settings to file: " + file.absolutePath)
        }
    }


    fun loadSettings(settings: AbstractSettings, file: File)
    {
        if (!file.exists()) return

        if (!file.canRead()) {
            Log.App.error("Cannot read file: " + file.absolutePath)
            return
        }

        val map = LinkedHashMap<String, String>()

        val lines: List<String> = file.readLines()
        for (line in lines)
        {
            val str = line.trim()
            if (str.isEmpty() || str.startsWith('#') || str.startsWith("--")) continue

            val nv = str divideTrim '='
            val name = nv.a
            val value = nv.b

            if (name.isNotEmpty() && value.isNotEmpty()) map[name] = value
        }

        settings.import(map)
    }




    fun saveUserSettings()
    {
        val workspaceFile = File(applicationSettingsDir, userWorkspaceFileName)
        saveSetting(UserWorkspace, workspaceFile)
    }

    fun loadUserSettings()
    {
        val workspaceFile = File(applicationSettingsDir, userWorkspaceFileName)
        loadSettings(UserWorkspace, workspaceFile)
    }
}