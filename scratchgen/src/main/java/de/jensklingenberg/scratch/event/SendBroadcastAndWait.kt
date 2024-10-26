package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class SendBroadcastAndWait(broadcast: Broadcast) : BlockSpec(
    opcode = OpCode.event_broadcastandwait,
    inputs = mapOf("BROADCAST_INPUT" to createBroadcast(broadcast))
), Event

fun ScriptBuilder.sendBroadcastAndWait(broadcast: Broadcast) = addChild(SendBroadcastAndWait(broadcast))