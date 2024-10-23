package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

class SwitchCostume(private val value: ReporterBlock) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val protoUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_switchcostumeto,
            inputs = mapOf("COSTUME" to JsonArray(listOf(JsonPrimitive(2), JsonPrimitive(protoUUID.toString())))),
        ).toBlock(nextUUID, parent, context.topLevel)

        value.visit(visitors, identifier.toString(), protoUUID, null, context)
    }
}

fun NodeBuilder.switchCostume(value: ReporterBlock) = addChild(SwitchCostume(value))