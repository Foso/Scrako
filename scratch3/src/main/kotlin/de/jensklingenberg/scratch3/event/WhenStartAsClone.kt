package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenStartAsClone : BlockSpec(
    opcode = "control_start_as_clone",
), Event, HatBlock

fun CommonScriptBuilder.whenStartAsClone() = addNode(WhenStartAsClone())
