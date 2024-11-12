package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Costume2
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class SwitchCostume(private val block: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val menuId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_switchcostumeto",
            inputs = mapOf(
                "COSTUME" to when (block) {
                    is CostumeMenu -> {
                        JsonArray(
                            listOf(
                                JsonPrimitive(1),
                                JsonPrimitive(menuId),
                            )
                        )
                    }

                    else -> setValue(block, menuId, context)
                }
            )
        ).toBlock(nextUUID, parent)

        block.visit(visitors, identifier, menuId, null, context)

    }
}


private class CostumeMenu(private val value: String) : ReporterBlock {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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
fun CommonScriptBuilder.switchCostume(value: Costume2) = addNode(SwitchCostume(CostumeMenu(value.name)))