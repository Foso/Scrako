package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

private class WhenKeyPress(key: Key) : BlockSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key.key, null))
), Event

fun NodeBuilder.whenKeyPress(key: Key) = addChild(WhenKeyPress(key))

