package de.jensklingenberg.scrako.common


class ScriptBuilder {

    val childs = mutableListOf<Node>()
    val variables = mutableSetOf<ScratchVariable>()
    val lists = mutableSetOf<ScratchList>()

    fun addChild(node: Node) {
        childs.add(node)
    }
}