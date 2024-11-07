package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull


private class SendBroadcastAndWait(val broadcast: Broadcast) : Node, Event {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val broadcastId = context.broadcastMap[broadcast.name] ?: "Cant find broadcast"
        visitors[identifier] = BlockSpec(
            opcode = "event_broadcastandwait",
            inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast.name, broadcastId))
        ).toBlock(nextUUID, parent)
    }
}

fun CommonScriptBuilder.sendBroadcastAndWait(broadcast: Broadcast) = addNode(SendBroadcastAndWait(broadcast))