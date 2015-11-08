@file:JvmName("CrocodileGena")
package lb.dmagus.crocodile


fun main(args: Array<String>)
{
    say("\n\nКрокодил Гена\n");

    val mm = readMetaModel()
    val templates = readTemplates()

    generate(mm, templates)

    say("\nOk.\n")
}


