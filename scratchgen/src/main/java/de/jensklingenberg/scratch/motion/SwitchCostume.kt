package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class SwitchCostume(private val block: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
        ) {
        val menuId = UUID.randomUUID()
        val blockId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_switchcostumeto,
            inputs = mapOf(
                "COSTUME" to when (block) {
                    is StringBlock -> JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(menuId.toString())))
                    else -> JsonArray(
                        listOf(
                            JsonPrimitive(3),
                            JsonPrimitive(menuId.toString()),
                            JsonPrimitive(blockId.toString())
                        )
                    )
                }
            ),
        ).toBlock(nextUUID, parent)

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


private class CostumeMenu(private val value: String? = "costume1") : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_costume,
            fields = mapOf("COSTUME" to listOf(value, null)),
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.switchCostume(value: ReporterBlock) = addChild(SwitchCostume(value))
fun ScriptBuilder.switchCostume(value: String) = addChild(SwitchCostume(StringBlock(value)))