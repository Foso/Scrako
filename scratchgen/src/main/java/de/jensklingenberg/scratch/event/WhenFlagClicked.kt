package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.HatBlock
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.NodeBuilder

fun NodeBuilder.whenFlagClicked() = addChild(WhenFlagClicked())


class WhenFlagClicked : BlockSpec(
    opcode = OpCode.Whenflagclicked,
), Event, HatBlock

