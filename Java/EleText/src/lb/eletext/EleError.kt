package lb.eletext



data class EleError (

    /**
     * Name of the file this line from.
     * Can be with a relative path.
     * The string delimiter is always '/' independent of the file system.
     */
    val file: String,

    /**
     * The line number, starts with 1.
     */
    val line: Int,

    /**
     * The position where the error occurred, or 0 if unknown
     */
    val pos: Int = 0,

    /**
     * The error message
     */
    val message: String

)