package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Node


class ScriptBuilder {

    val childs = mutableListOf<Node>()
    val functionsMap = mutableMapOf<String, List<String>>()
    fun addNode(node: Node) {
        childs.add(node)
    }
}