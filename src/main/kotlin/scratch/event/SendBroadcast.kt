package me.jens.scratch.event

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import me.jens.Event
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode
import scratch.Broadcast

class SendBroadcast(private val broadcast: Broadcast) : BlockSpecSpec(
    opcode = OpCode.event_broadcast,
    inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast))
), Event

private fun createBroadcast(operatorId: Broadcast) = JsonArray(
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