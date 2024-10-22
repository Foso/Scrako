package me.jens.scratch.event

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode

class WhenKeyPress(key: Key) : BlockSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key.key, null))
), Event

