package de.jensklingenberg.scratch.common

class NodeBuilder {

    fun addChild(whenFlagClicked: Node) {
        childs.add(whenFlagClicked)
    }

    val childs = mutableListOf<Node>()
}