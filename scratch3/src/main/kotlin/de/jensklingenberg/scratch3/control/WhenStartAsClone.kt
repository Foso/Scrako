package de.jensklingenberg.scratch3.control

import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenStartAsClone : BlockSpec(
    opcode = "control_start_as_clone",
), Event, HatBlock

fun SpriteScriptBuilder.whenStartAsClone() = addNode(WhenStartAsClone())
