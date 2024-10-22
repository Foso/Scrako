package me.jens.scratch.motion

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock
import java.util.UUID

class SwitchCostume(private val value: ReporterBlock) : Node {

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
            opcode = OpCode.looks_switchcostumeto,
            inputs = mapOf("COSTUME" to JsonArray(listOf(JsonPrimitive(2), JsonPrimitive(protoUUID.toString())))),
        ).toBlock(nextUUID?.toString(), parent, index == 0 && layer == 0)

        value.visit(visitors, identifier.toString(), 0, protoUUID, null, layer+1, context)
    }
}