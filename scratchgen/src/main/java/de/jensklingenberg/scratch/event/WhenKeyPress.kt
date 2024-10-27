package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.HatBlock
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ScriptBuilder

private class WhenKeyPress(key: Key) : BlockSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key.key, null))
), Event, HatBlock

fun ScriptBuilder.whenKeyPress(key: Key) = addChild(WhenKeyPress(key))

