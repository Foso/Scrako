package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.model.Block
import java.util.UUID

data class Context(val parent: String? = null, val topLevel: Boolean)

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int = 0,
        context: Context,
    )
}