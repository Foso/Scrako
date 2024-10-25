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

private class SwitchCostume(private val block: ReporterBlock) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val menuId = UUID.randomUUID()
        val blockId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_switchcostumeto,
            inputs = mapOf(
                "COSTUME" to when (block) {
                    is StringBlock -> JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(menuId.toString())))
                    else -> JsonArray(listOf(JsonPrimitive(3), JsonPrimitive(menuId.toString()),JsonPrimitive(blockId.toString())))
                }
            ),
        ).toBlock(nextUUID, parent, context.topLevel)

        when (block) {
            is StringBlock -> {
                CostumeMenu(block.value).visit(visitors, identifier.toString(), menuId, null, context)

            }
            else -> {
                CostumeMenu().visit(visitors, identifier.toString(), menuId, null, context)
                block.visit(visitors, identifier.toString(), menuId, null, context)
            }
        }

    }
}


private class CostumeMenu(private val value: String?="costume1") : Node {

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

fun NodeBuilder.switchCostume(value: ReporterBlock) = addChild(SwitchCostume(value))
fun NodeBuilder.switchCostume(value: String) = addChild(SwitchCostume(StringBlock(value)))