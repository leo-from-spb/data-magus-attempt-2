package lb.dmagus.crocodile

/**
 * @author Leonid Bushuev from JetBrains
 **/


fun adjustModel(mm: MetaModel)
{

    for (node in mm.nodes)
    {
        if (!node.top)
        {
            node.parent = mm.classes[node.parentClass]
            assert(node.parent != null)
        }
        else
        {
            node.parent = modelNode
        }
    }

}

val modelNode = MetaNode(pack = "",
                         name = "model",
                         klass = "Model")
