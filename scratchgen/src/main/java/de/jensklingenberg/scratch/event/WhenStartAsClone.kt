package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.HatBlock
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class WhenStartAsClone : BlockSpec(
    opcode = OpCode.control_start_as_clone,
), Event, HatBlock

fun ScriptBuilder.whenStartAsClone() = addChild(WhenStartAsClone())
