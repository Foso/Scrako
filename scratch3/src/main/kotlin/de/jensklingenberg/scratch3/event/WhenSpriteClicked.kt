package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenSpriteClicked : BlockSpec(
    opcode = "event_whenthisspriteclicked",
), Event, HatBlock

fun SpriteScriptBuilder.whenSpriteClicked() = addNode(WhenSpriteClicked())
