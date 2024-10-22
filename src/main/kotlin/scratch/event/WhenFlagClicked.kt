package me.jens.scratch.event

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode

class WhenFlagClicked(x: Int = 0, y: Int = 0) : BlockSpec(
    opcode = OpCode.Whenflagclicked,
    x = x,
    y = y
), Event, HatBlock

interface HatBlock