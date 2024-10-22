package me.jens.scratch.motion

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock
import me.jens.scratch.common.createMessage

class ChangeYby(private val value: Int) : BlockSpec(
    opcode = OpCode.motion_changeyby,
    inputs = mapOf(
        "DY" to createMessage(
            1, 4, value.toString()
        )
    )
), ReporterBlock