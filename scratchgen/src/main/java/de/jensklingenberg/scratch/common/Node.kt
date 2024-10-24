package de.jensklingenberg.scratch.common

import de.jensklingenberg.scratch.model.Block
import java.util.UUID

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
    ){}
}