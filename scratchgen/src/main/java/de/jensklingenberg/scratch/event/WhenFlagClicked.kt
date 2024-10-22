package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.NodeBuilder

fun NodeBuilder.whenFlagClicked(x: Int = 0, y: Int = 0) = addChild(WhenFlagClicked(x, y))


class WhenFlagClicked(x: Int = 0, y: Int = 0) : BlockSpec(
    opcode = OpCode.Whenflagclicked,
    x = x,
    y = y
), Event, HatBlock

interface HatBlock