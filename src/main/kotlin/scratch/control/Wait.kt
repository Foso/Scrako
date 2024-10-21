package me.jens.scratch.control

import me.jens.createMessage
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode

class Wait(seconds: Int) : BlockSpecSpec(
    opcode = OpCode.ControlWait,
    inputs = mapOf(
        "DURATION" to createDuration(seconds.toString())
    )
)

private fun createDuration(message: String) = createMessage(1, 5, message)