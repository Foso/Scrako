package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Argument2
import de.jensklingenberg.scrako.common.Node


@DslMarker
annotation class ScriptDsl

@ScriptDsl
open class ScriptBuilder {

    val childs = mutableListOf<Node>()
    val functionsMap = mutableMapOf<String, List<Argument2>>()
    fun addNode(node: Node) {
        childs.add(node)
    }
}