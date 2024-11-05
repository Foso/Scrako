package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

private class SendBroadcast(val broadcast: Broadcast) : Node, Event {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val entry = context.broadcastMap[broadcast.name] ?: "Cant find broadcast"
        visitors[identifier] = BlockSpec(
            opcode = "event_broadcast",
            inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast.name, entry))
        ).toBlock(nextUUID, parent)
    }
}

internal fun createBroadcast(name: String, id: String) = JsonArray(
    listOf(
        JsonPrimitive(1),
        JsonArray(
            listOf(
                JsonPrimitive(11),
                JsonPrimitive(name),
                JsonPrimitive(id)
            )
        )
    )
)

fun CommonScriptBuilder.sendBroadcast(broadcast: Broadcast) = addNode(SendBroadcast(broadcast))