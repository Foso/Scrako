package de.jensklingenberg.scrako.common

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.model.Block


interface Node {
    fun visit(
        visitors: MutableMap<String, Block>,
        parent: String? = null,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
    }
}