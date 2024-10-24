package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.ScratchList

class NodeBuilder {

    fun addChild(whenFlagClicked: Node) {
        childs.add(whenFlagClicked)
    }

    val childs = mutableListOf<Node>()
    val variables = mutableSetOf<ScratchVariable>()
    val lists = mutableSetOf<ScratchList>()
}