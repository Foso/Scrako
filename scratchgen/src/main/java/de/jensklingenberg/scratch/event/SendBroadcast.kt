package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

class SendBroadcast(broadcast: de.jensklingenberg.scratch.Broadcast) : BlockSpec(
    opcode = OpCode.event_broadcast,
    inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast))
), Event

class SendBroadcastAndWait(broadcast: de.jensklingenberg.scratch.Broadcast) : BlockSpec(
    opcode = OpCode.event_broadcastandwait,
    inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast))
), Event

private fun createBroadcast(operatorId: de.jensklingenberg.scratch.Broadcast) = JsonArray(
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