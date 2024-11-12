package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.CapBlock
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.common.Node

open class CommonScriptBuilder {

    val childs = mutableListOf<Node>()
    val functionsMap = mutableMapOf<String, List<Argument>>()
    fun addNode(node: Node) {
        if (childs.isNotEmpty() && childs.last() is CapBlock) {
            throw IllegalArgumentException("You can't add a block after a CapBlock")
        }
        if (node is HatBlock && childs.isNotEmpty()) {
            throw IllegalArgumentException(node.javaClass.simpleName + " needs to be the first block in script")
        }
        childs.add(node)
    }
}


class SpriteScriptBuilder : CommonScriptBuilder()
class StageScriptBuilder : CommonScriptBuilder()

