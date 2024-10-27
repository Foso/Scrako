package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
private class WhenIRecieve(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_whenbroadcastreceived,
    fields = mapOf("BROADCAST_OPTION" to listOf(broadcast.name, broadcast.id.toString()))
), Event, HatBlock

fun ScriptBuilder.whenIRecieve(broadcast: Broadcast) = addChild(WhenIRecieve(broadcast))