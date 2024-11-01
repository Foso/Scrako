package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scratch3.common.OpCode
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

fun ScriptBuilder.sendBroadcast(broadcast: Broadcast) = addNode(SendBroadcast(broadcast))