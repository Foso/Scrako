package de.jensklingenberg.scrako.common


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