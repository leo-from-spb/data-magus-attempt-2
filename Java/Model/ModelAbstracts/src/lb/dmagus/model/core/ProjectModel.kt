package lb.dmagus.model.core


interface ProjectModel: Model {

    val project: Project
    override val root: Root get() = project
    
}