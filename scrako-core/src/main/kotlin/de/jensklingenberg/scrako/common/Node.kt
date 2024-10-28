package de.jensklingenberg.scrako.common

import java.util.UUID


data class Context(val variableMap: Map<String, ScratchVariable>)

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
    }
}