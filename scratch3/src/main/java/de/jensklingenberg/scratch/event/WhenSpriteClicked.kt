package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scratch.common.OpCode

private class WhenSpriteClicked : BlockSpec(
    opcode = OpCode.event_whenthisspriteclicked,
), Event, HatBlock

fun ScriptBuilder.whenSpriteClicked() = addNode(WhenSpriteClicked())