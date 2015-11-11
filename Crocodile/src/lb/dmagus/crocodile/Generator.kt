package lb.dmagus.crocodile

import freemarker.template.Configuration
import java.io.FileWriter

/**
 * Generates Model Skeleton using Free-Marker templates.
 * @author Leonid Bushuev from JetBrains
 **/
fun generate(mm: MetaModel)
{

    for (node in mm.nodes)
    {
        generateFile(FileContext(node, mm))
    }

}




private fun generateFile(context: FileContext)
{
    val template = FreeMarkerConfiguration.getTemplate("ModelElement.ftl")
    val filePath = baseTargetDir.resolve(context.n.pack).resolve(context.n.klass + ".kt")
    val fileWriter = FileWriter(filePath.toFile())

    fileWriter.use {
        template.process(context, it)
        it.flush()
    }
}

internal object FreeMarkerConfiguration : Configuration(Configuration.VERSION_2_3_22)
{
    init
    {
        val templateDir = baseMetaDir.resolve("templates")
        setDirectoryForTemplateLoading(templateDir.toFile())
        setDefaultEncoding("UTF-8")

    }
}

