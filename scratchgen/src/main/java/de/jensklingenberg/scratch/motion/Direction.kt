package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch.common.OpCode

object Direction : BlockSpec(
    opcode = OpCode.motion_direction,
), ReporterBlock


