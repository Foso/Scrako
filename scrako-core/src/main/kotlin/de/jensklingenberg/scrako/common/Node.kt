package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.model.BlockFull


interface Node {
    fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String? = null,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
    }
}