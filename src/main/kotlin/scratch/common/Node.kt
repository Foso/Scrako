package me.jens.scratch.common

import me.jens.scratch.Block
import java.util.UUID

class Context(val parent: String? = null)

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int = 0,
        context: Context,
    )
}