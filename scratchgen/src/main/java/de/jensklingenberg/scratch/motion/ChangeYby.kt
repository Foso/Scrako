package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage

class ChangeYby(private val value: Int) : BlockSpec(
    opcode = OpCode.motion_changeyby,
    inputs = mapOf(
        "DY" to createMessage(
            1, 4, value.toString()
        )
    )
), ReporterBlock