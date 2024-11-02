package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.Node

@DslMarker
annotation class ScriptDsl

@ScriptDsl
open class CommonScriptBuilder {

    val childs = mutableListOf<Node>()
    val functionsMap = mutableMapOf<String, List<Argument>>()
    fun addNode(node: Node) {
        childs.add(node)
    }
}

class SpriteScriptBuilder : CommonScriptBuilder()
class StageScriptBuilder : CommonScriptBuilder()