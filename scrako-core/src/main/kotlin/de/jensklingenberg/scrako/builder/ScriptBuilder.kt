package de.jensklingenberg.scrako.builder

import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable


class ScriptBuilder {

    val childs = mutableListOf<Node>()
   // internal val variables = mutableSetOf<ScratchVariable>()
    val variableMap = mutableMapOf<String, ScratchVariable>()
    val lists = mutableSetOf<ScratchList>()


    fun addVariable(name: ScratchVariable) {
        //variables.add(name)
        variableMap[name.name] = ScratchVariable(name.name)
    }

    fun addNode(node: Node) {
        childs.add(node)
    }
}