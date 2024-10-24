package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.HatBlock
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode


private class WhenFlagClicked : BlockSpec(
    opcode = OpCode.Whenflagclicked,
), Event, HatBlock


fun NodeBuilder.whenFlagClicked() = addChild(WhenFlagClicked())
