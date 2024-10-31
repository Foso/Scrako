package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class PointTowards(private val target: String) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context) {
        val uuid = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.motion_pointtowards,
            inputs = mapOf(
                "TOWARDS" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(uuid.toString())))
            )
        ).toBlock(nextUUID, parent)

        PointTowardsMenu(target).visit(visitors, identifier, uuid, null, context)
    }
}

fun ScriptBuilder.pointTowards(target: String) = addNode(PointTowards(target))

private class PointTowardsMenu(target: String) : BlockSpec(
    opcode = OpCode.motion_pointtowards_menu,
    fields = mapOf("TOWARDS" to listOf(target, null))
)
