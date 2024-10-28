package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable


class ScriptBuilder {

    val childs = mutableListOf<Node>()
    val variables = mutableSetOf<ScratchVariable>()
    val lists = mutableSetOf<ScratchList>()

    fun addChild(node: Node) {
        childs.add(node)
    }
}