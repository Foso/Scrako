package de.jensklingenberg.scrako.common

import java.util.UUID


class Context(val variables: Set<ScratchVariable> =emptySet())

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ){}
}