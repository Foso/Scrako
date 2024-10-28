package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchList


class ScriptBuilder {

    val childs = mutableListOf<Node>()
   // internal val variables = mutableSetOf<ScratchVariable>()

    val lists = mutableSetOf<ScratchList>()

    fun addNode(node: Node) {
        childs.add(node)
    }
}