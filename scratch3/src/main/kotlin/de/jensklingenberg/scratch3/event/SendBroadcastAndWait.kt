package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scratch3.common.OpCode

private class SendBroadcastAndWait(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_broadcastandwait,
    inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast))
), Event

fun ScriptBuilder.sendBroadcastAndWait(broadcast: Broadcast) = addNode(SendBroadcastAndWait(broadcast))