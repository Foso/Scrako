package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.common.OpCode

private class WhenIRecieve(val broadcast: Broadcast) : Node, Event, HatBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        BlockSpec(
            opcode = OpCode.event_whenbroadcastreceived,
            fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.whenIRecieve(broadcast: Broadcast) = addNode(WhenIRecieve(broadcast))