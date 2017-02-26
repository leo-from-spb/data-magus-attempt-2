package lb.eletext



data class EleError (

    /**
     * Name of the file this line from.
     * Can be with a relative path.
     * The string delimiter is always '/' independent of the file system.
     */
    val fileName: String,

    /**
     * The line number, starting with 1.
     */
    val lineNr: Int,

    /**
     * The position where the error occurred, starting with 1.
     * Zero if unknown.
     */
    val pos: Int = 0,

    /**
     * The error message
     */
    val message: String

)