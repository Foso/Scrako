package de.jensklingenberg.scrako.common


class ScriptBuilder {

    fun addChild(whenFlagClicked: Node) {
        childs.add(whenFlagClicked)
    }

    val childs = mutableListOf<Node>()
    val variables = mutableSetOf<ScratchVariable>()
    val lists = mutableSetOf<ScratchList>()
}