package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.common.OpCode

private class SendBroadcastAndWait(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_broadcastandwait,
    inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast))
), Event

fun ScriptBuilder.sendBroadcastAndWait(broadcast: Broadcast) = addChild(SendBroadcastAndWait(broadcast))