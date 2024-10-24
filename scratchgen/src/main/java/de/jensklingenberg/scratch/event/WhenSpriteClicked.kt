package de.jensklingenberg.scratch.event

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.HatBlock
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

private class WhenSpriteClicked : BlockSpec(
    opcode = OpCode.event_whenthisspriteclicked,
), Event, HatBlock

fun NodeBuilder.whenSpriteClicked() = addChild(WhenSpriteClicked())