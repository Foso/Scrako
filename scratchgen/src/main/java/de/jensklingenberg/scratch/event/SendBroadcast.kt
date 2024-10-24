package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

private class SendBroadcast(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_broadcast,
    inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast))
), Event

internal fun createBroadcast(operatorId: Broadcast) = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(11),
                JsonPrimitive(operatorId.name),
                JsonPrimitive(operatorId.id.toString())
            )
        )
    )
)

fun NodeBuilder.sendBroadcast(broadcast: Broadcast) = addChild(SendBroadcast(broadcast))