package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class SwitchCostume(private val block: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val menuId = UUID.randomUUID().toString()
        val blockId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_switchcostumeto",
            inputs = mapOf(
                "COSTUME" to when (block) {
                    is StringBlock -> JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(menuId)))
                    else -> JsonArray(
                        listOf(
                            JsonPrimitive(3),
                            JsonPrimitive(menuId),
                            JsonPrimitive(blockId)
                        )
                    )
                }
            ),
        ).toBlock(nextUUID, parent)

        when (block) {
            is StringBlock -> {
                CostumeMenu(block.value).visit(visitors, identifier, menuId, null, context)

            }

            else -> {
                CostumeMenu().visit(visitors, identifier, menuId, null, context)
                block.visit(visitors, identifier, menuId, null, context)
            }
        }

    }
}


private class CostumeMenu(private val value: String? = "costume1") : ReporterBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "looks_costume",
            fields = mapOf("COSTUME" to listOf(value, null)),
        ).toBlock(nextUUID, parent)
    }
}

fun CommonScriptBuilder.switchCostume(block: ReporterBlock) = addNode(SwitchCostume(block))
fun CommonScriptBuilder.switchCostume(value: String) = addNode(SwitchCostume(CostumeMenu(value)))