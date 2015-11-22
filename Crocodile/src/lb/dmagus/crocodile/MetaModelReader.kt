@file:JvmName("MetaModelReader")
package lb.dmagus.crocodile

import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.text.MatchResult
import kotlin.text.Regex


val mDefPattern = Regex("^\\w+\\.mdef$")
val mTemplatePattern = Regex("^\\w+\\.template$")

val mFilePattern   = regex("^(\\w+) : (\\w+) @ (\\w+) ((\\+ (\\w+) )+)?$")
val mFamilyPattern = regex("^  \\+ (\\w+) $")
val mRefPattern    = regex("^  \\. (\\w+) (--?>>?) (\\w+) $")
val mPropPattern   = regex("^  \\. (\\w+) : (\\w+) = (\\w+) $")


fun readMetaModel() : MetaModel
{
    val mm = MetaModel()

    val modelDir = baseMetaDir.resolve("model")
    assert(modelDir != null) { "Expect that the model directory exists: $modelDir" }
    Files.newDirectoryStream(modelDir).use { stream ->
        stream
            .filter { f -> f.fileName.toString().matches(mDefPattern) }
            .forEach { f -> readMetaFile(mm, f) }
    }

    return mm
}


fun readMetaFile(mm: MetaModel, file: Path)
{
    val pack = file.fileName.toString().substringBefore('.')
    say("Reading package: $pack")

    val lines = ArrayList<String>()

    Files.lines(file)
        .filter {s -> s != null && s.isNotEmpty()}
        .forEach{s -> lines.add(s)}
    val n = lines.size;

    say("\t- $n lines")

    for (k in 0..n-1)
    {
        val fileLine = lines[k]
        if (fileLine[0].isWhitespace()) continue

        val m = fileLine match mFilePattern
        assert(m != null) {"Failed to parse line: $fileLine"}
        m!!

        val f = MetaNode()

        val interfacesString = m.groups[4]?.value
        if (interfacesString != null) handleInterfaces(f, interfacesString)

        f.pack        = pack
        f.klass       = m.groups[1]!!.value
        f.base        = m.groups[2]!!.value
        f.parentClass = m.groups[3]!!.value
        f.parentName  = class2name(f.parentClass)
        f.name        = class2name(f.klass)

        for (i in k+1..n-1)
        {
            val line = lines[i]
            if (!line[0].isWhitespace()) break

            val fm = line match mFamilyPattern
            if (fm != null) handleFamily(f, fm)

            val rm = line match mRefPattern
            if (rm != null) handleRef(f, rm)

            val pm = line match mPropPattern
            if (pm != null) handleProperty(f, pm)
        }

        mm.nodes.add(f)
        mm.classes.put(f.klass, f)
    }
}


fun handleInterfaces(f: MetaNode, interfacesString: String)
{
    interfacesString
        .splitToSequence('+')
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .forEach { f.interfaces.add(it) }
}

fun handleFamily(f: MetaNode, pm: MatchResult)
{
    val familyName = pm.groups[1]!!.value;
    f.families.add(MetaFamily(familyName))
}

fun handleRef(f: MetaNode, m: MatchResult)
{
    val refName = m.groups[1]!!.value
    val targetClass = m.groups[3]!!.value
    val refType = m.groups[2]!!.value
    val r = MetaRef(refName,
                    targetClass,
                    "--" in refType,
                    ">>" in refType,
                    true)
    f.refs.add(r)
}

fun handleProperty(f: MetaNode, m: MatchResult)
{
    val p = MetaProperty( m.groups[1]!!.value,
                          m.groups[2]!!.value,
                          m.groups[3]!!.value )
    f.properties.add(p)
}



fun readTemplates(): Map<String,String>
{
    say("Reading templates:")

    val templates = HashMap<String,String>()

    val modelDir = baseMetaDir.resolve("templates")
    assert(modelDir != null) { "Expect that the model directory exists: $modelDir" }
    Files.newDirectoryStream(modelDir).use { stream ->
        stream
            .filter { f -> f.fileName.toString().matches(mTemplatePattern) }
            .forEach { f -> readTemplateFile(templates, f) }
    }

    val n = templates.size
    assert(n >= 3) {"Too few template files: $n"}

    return templates
}

fun readTemplateFile(templates: MutableMap<String, String>, f: Path)
{
    val name = f.fileName.toString().substringBefore('.')
    val text = String(Files.readAllBytes(f))
    templates.put(name,text)
    say("\t- $name (${text.length} characters)")
}
