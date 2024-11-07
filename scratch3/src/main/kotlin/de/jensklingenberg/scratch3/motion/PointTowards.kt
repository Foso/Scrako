package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class PointTowards(private val target: String) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val uuid = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "motion_pointtowards",
            inputs = mapOf(
                "TOWARDS" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(uuid)))
            )
        ).toBlock(nextUUID, parent)

        PointTowardsMenu(target).visit(visitors, identifier, uuid, null, context)
    }
}

fun CommonScriptBuilder.pointTowards(target: String) = addNode(PointTowards(target))

private class PointTowardsMenu(target: String) : BlockSpec(
    opcode = "motion_pointtowards_menu",
    fields = mapOf("TOWARDS" to listOf(target, null))
)
