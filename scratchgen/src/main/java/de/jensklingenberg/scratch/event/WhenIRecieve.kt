package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.HatBlock
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

private class WhenIRecieve(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_whenbroadcastreceived,
    fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
), Event, HatBlock

fun NodeBuilder.whenIRecieve(broadcast: Broadcast) = addChild(WhenIRecieve(broadcast))