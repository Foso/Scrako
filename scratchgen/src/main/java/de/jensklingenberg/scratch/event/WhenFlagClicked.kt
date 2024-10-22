package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode


class WhenFlagClicked(x: Int = 0, y: Int = 0) : BlockSpec(
    opcode = OpCode.Whenflagclicked,
    x = x,
    y = y
), Event, HatBlock

interface HatBlock