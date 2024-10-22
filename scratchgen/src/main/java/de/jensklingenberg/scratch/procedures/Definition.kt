package de.jensklingenberg.scratch.procedures

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import de.jensklingenberg.scratch.common.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

class Definition(private val prototypeName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        val protoUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.procedures_definition,
            inputs = mapOf("custom_block" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(protoUUID.toString())))),
        ).toBlock(nextUUID?.toString(), parent, index == 0)

        Prototype(this.prototypeName).visit(visitors, identifier.toString(), 0, protoUUID, null, 1, context)
    }
}