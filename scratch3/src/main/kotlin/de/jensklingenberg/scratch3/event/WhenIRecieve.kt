package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block

private class WhenIRecieve(val broadcast: Broadcast) : Node, Event, HatBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        if (parent != null) {
            throw IllegalStateException(this::class.simpleName + " blocks can't have a parent")
        }
        val broadcastId = context.broadcastMap[broadcast.name] ?: "Cant find broadcast"
        visitors[identifier] = BlockSpec(
            opcode = "event_whenbroadcastreceived",
            fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcastId))
        ).toBlock(nextUUID, parent)
    }
}

fun CommonScriptBuilder.whenIReceiveBroadcast(broadcast: Broadcast) = addNode(WhenIRecieve(broadcast))