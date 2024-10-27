package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class PointTowards(private val target: String) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_pointtowards,
            inputs = mapOf(
                "TOWARDS" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(uuid.toString())))
            )
        ).toBlock(nextUUID, parent)

        PointTowardsMenu(target).visit(visitors, identifier.toString(), uuid, null, )
    }
}

fun ScriptBuilder.pointTowards(target: String) = addChild(PointTowards(target))

private class PointTowardsMenu(target: String) : BlockSpec(
    opcode = OpCode.motion_pointtowards_menu,
    fields = mapOf("TOWARDS" to listOf(target, null))
)
