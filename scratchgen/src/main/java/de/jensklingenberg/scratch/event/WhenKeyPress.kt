package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class WhenKeyPress(key: Key) : BlockSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key.key, null))
), Event, HatBlock

fun ScriptBuilder.whenKeyPress(key: Key) = addChild(WhenKeyPress(key))

