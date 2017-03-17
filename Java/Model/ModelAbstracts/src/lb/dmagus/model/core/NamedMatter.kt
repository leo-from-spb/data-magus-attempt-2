package lb.dmagus.model.core


interface NamedMatter: Matter {

    var name: String?

    override val displayName: String
        get() = name ?: "(unnamed)"
    
}