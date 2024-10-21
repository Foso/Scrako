package me.jens.scratch.common

import scratch.Block
import java.util.UUID

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int = 0
    )
}