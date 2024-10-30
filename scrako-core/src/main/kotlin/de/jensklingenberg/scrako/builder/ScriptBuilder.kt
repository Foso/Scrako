package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.Node


open class ScriptBuilder {

    val childs = mutableListOf<Node>()
    val functionsMap = mutableMapOf<String, List<Argument>>()
    fun addNode(node: Node) {
        childs.add(node)
    }
}