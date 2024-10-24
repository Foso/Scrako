package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.HatBlock
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

fun NodeBuilder.whenFlagClicked() = addChild(WhenFlagClicked())


class WhenFlagClicked : BlockSpec(
    opcode = OpCode.Whenflagclicked,
), Event, HatBlock


class WhenStartAsClone : BlockSpec(
    opcode = OpCode.control_start_as_clone,
), Event, HatBlock

fun NodeBuilder.whenStartAsClone() = addChild(WhenStartAsClone())