package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode

class WhenKeyPress(key: Key) : BlockSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key.key, null))
), Event

