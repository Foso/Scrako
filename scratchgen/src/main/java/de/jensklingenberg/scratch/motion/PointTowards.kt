package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

class PointTowards(private val target: String) : Node{

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_pointtowards,
            inputs = mapOf(
                "TOWARDS" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(uuid.toString())))
            )
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)

        PointTowardsMenu(target).visit(visitors, identifier.toString(), uuid, null, context.copy(topLevel = false))
    }
}

fun NodeBuilder.pointTowards(target: String) = addChild(PointTowards(target))

private class PointTowardsMenu(target: String) : BlockSpec(
    opcode = OpCode.motion_pointtowards_menu,
    fields = mapOf("TOWARDS" to listOf(target, null))
)
