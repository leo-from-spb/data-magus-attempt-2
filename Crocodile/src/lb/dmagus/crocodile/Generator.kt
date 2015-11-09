package lb.dmagus.crocodile

import lb.dmagus.util.plural
import java.nio.file.Files


fun generate(mm: MetaModel, templates: Map<String, String>)
{
    assert(baseTargetDir.toFile().isDirectory) { "Expected that the following directory exists: $baseTargetDir" }

    for (f in mm.files)
    {
        generateFile(f, mm, templates)
    }
}

private fun generateFile(f: MetaFile, mm: MetaModel, templates: Map<String, String>)
{
    say("\nGenerating file: ${f.pack}/${f.klass}")

    val b = StringBuilder(getTemplate(templates, "file"))

    removeUnneededSections(b, f)

    b replace ("INTERFACES" to f.interfaces.fold("", {a,b -> a+", "+b}))
    b replace ("PARENT_NAME" to f.parentName)
    b replace ("PARENT_CLASS" to f.parentClass)
    b replace ("PACK" to f.pack)
    b replace ("OPEN" to f.open)
    b replace ("CLASS" to f.klass)
    b replace ("NAME" to f.name)
    b replace ("BASE" to f.base)



    generateFamilies(f, mm, templates, b)
    generateProperties(f, mm, templates, b)

    val filePath = baseTargetDir.resolve(f.pack).resolve(f.klass+".kt")
    Files.write(filePath, b.toString().toByteArray())
}

fun generateFamilies(f: MetaFile, mm: MetaModel, templates: Map<String, String>, b: StringBuilder)
{
    if (f.families.isEmpty())
    {
        b removeSection "Families"
        return
    }

    val template = templates["family"]!!

    val ba: StringBuilder = StringBuilder()
    ba.append('\n')

    var famList = ""

    for (famClass in f.families)
    {
        val fam = mm.classes[famClass]!!

        if (famList.isNotEmpty()) famList += ','
        famList += fam.name.plural

        val bf = StringBuilder(template)

        bf replace ("|FAM_NAMES|" to fam.name.plural)
        bf replace ("|FAM_CLASS|" to fam.klass)

        ba.append(bf).append('\n')
    }

    b replace ("|FAMILIES|" to ba.toString())
    b replace ("|FAMILY_LIST|" to famList)
}


fun generateProperties(f: MetaFile, mm: MetaModel, templates: Map<String, String>, b: StringBuilder)
{
    if (f.properties.isEmpty())
    {
        b removeSection "Properties"
        return
    }

    val template = templates["property"]!!

    val ba: StringBuilder = StringBuilder()
    ba.append('\n')

    for (p in f.properties)
    {
        val pf = StringBuilder(template)

        pf replace ("|PROP_NAME|"    to p.name)
        pf replace ("|PROP_TYPE|"    to p.type)
        pf replace ("|PROP_DEFAULT|" to p.default)

        ba.append(pf).append('\n')
    }

    b replace ("|PROPERTIES|" to ba.toString())
}


private fun removeUnneededSections(b: StringBuilder, f: MetaFile)
{
    if ("TopSpace" in f.interfaces)  b removeSection "Normal"
    else                             b removeSection "TopSpace"
}

private infix fun StringBuilder.removeSection(sectionName: String)
{
    this remove ("// +$sectionName" to "// -$sectionName")
}

private fun getTemplate(templates: Map<String, String>, name: String): String
{
    val text = templates[name]
    assert(text != null) { "Template $name not found" }
    return text!!
}
