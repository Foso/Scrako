package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class WhenStartAsClone : BlockSpec(
    opcode = OpCode.control_start_as_clone,
), Event, HatBlock

fun ScriptBuilder.whenStartAsClone() = addChild(WhenStartAsClone())
