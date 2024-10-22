package me.jens.scratch.control

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.createMessage

class Wait(seconds: Int) : BlockSpec(
    opcode = OpCode.ControlWait,
    inputs = mapOf(
        "DURATION" to createDuration(seconds.toString())
    )
)

private fun createDuration(message: String) = createMessage(1, 5, message)