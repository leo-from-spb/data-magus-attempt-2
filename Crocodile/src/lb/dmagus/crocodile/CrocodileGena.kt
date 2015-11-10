@file:JvmName("CrocodileGena")
package lb.dmagus.crocodile


fun main(args: Array<String>)
{
    say("\n\nКрокодил Гена\n");

    val mm = readMetaModel()

    adjustModel(mm)

    generate(mm)

    say("\nOk.\n")
}


