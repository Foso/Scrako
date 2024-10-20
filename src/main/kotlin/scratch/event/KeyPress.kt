package me.jens.scratch.event

import me.jens.Event
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode

class KeyPress(val key: String) : BlockSpecSpec(
    opcode = OpCode.event_whenkeypressed,
    fields = mapOf("KEY_OPTION" to listOf(key, null))
), Event

