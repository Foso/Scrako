package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

object Direction : BlockSpec(
    opcode = OpCode.motion_direction,
), ReporterBlock


