package de.jensklingenberg.scrako.common

import java.util.UUID

interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
    ) {
    }
}