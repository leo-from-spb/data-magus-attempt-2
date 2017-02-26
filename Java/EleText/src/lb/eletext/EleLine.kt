package lb.eletext

/**
 * One string of the Elegant Text file.
 */
data class EleLine (

    /**
     * Name of the file this line from.
     * Can be with a relative path.
     * The string delimiter is always '/' independent of the file system.
     */
    val file: String,

    /**
     * The line number, starts with 1.
     */
    val lineNr: Int,

    /**
     * The offset before the first character in the [rawString].
     * Zero means no offset; -1 means that the line is empty.
     */
    val offset: Int,

    /**
     * The raw string (with offset, as in the file).
     * Tab characters inm the offset are replaced with spaces.
     */
    val rawString: String

) {

    /**
     * Length of the [raw string][rawString].
     */
    val rawLength: Int
        get() = rawString.length

    /**
     * The length of the [meaningful string][meanString].
     */
    val meanLength: Int
        get() = if (offset >= 0) rawLength-offset else 0

    /**
     * The meaningful string.
     */
    val meanString: String
        get() =
            when (offset) {
                -1   -> ""
                0    -> rawString
                else -> rawString.substring(offset)
            }

}

