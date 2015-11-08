package lb.dmagus.crocodile

import java.nio.file.Files


fun generate(mm: MetaModel, templates: Map<String, String>)
{
    assert(baseTargetDir.toFile().isDirectory) { "Expected that the following directory exists: $baseTargetDir" }

    for (f in mm.files)
    {
        generateFile(f, templates)
    }
}

private fun generateFile(f: MetaFile, templates: Map<String, String>)
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

    val filePath = baseTargetDir.resolve(f.pack).resolve(f.klass+".kt")
    Files.write(filePath, b.toString().toByteArray())

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
