package lb.dmagus.model.core


interface FarmModel: Model {

    val farm: Farm
    override val root: Root get() = farm

}