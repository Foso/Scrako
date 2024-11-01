package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenStartAsClone : BlockSpec(
    opcode = "control_start_as_clone",
), Event, HatBlock

fun ScriptBuilder.whenStartAsClone() = addNode(WhenStartAsClone())
