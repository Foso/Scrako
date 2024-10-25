package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class SwitchCostume(private val value: CostumeMenu) : Node {

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
            inputs = mapOf("COSTUME" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(protoUUID.toString())))),
        ).toBlock(nextUUID, parent, context.topLevel)

        value.visit(visitors, identifier.toString(), protoUUID, null, context)
    }
}



private class CostumeMenu(private val value: String) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_costume,
            fields = mapOf("COSTUME" to listOf(value, null)),
        ).toBlock(nextUUID, parent, context.topLevel)


    }
}

fun NodeBuilder.switchCostume(value: String) = addChild(SwitchCostume(CostumeMenu(value)))