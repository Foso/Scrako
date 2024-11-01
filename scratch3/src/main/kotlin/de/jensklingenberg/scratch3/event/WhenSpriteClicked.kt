package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenSpriteClicked : BlockSpec(
    opcode = "event_whenthisspriteclicked",
), Event, HatBlock

fun ScriptBuilder.whenSpriteClicked() = addNode(WhenSpriteClicked())