package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.model.Block
import java.util.UUID

data class Context(val parent: String? = null, val topLevel: Boolean)

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
    )
}