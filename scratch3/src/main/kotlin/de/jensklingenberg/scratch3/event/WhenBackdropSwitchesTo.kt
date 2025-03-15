package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenBackdropSwitchesTo(backdrop: String) : BlockSpec(
    opcode = "event_whenbackdropswitchesto",
    fields = mapOf("BACKDROP" to listOf(backdrop, null))
), Event, HatBlock

fun CommonScriptBuilder.whenBackdropSwitchesTo(backdrop: String) = addNode(WhenBackdropSwitchesTo(backdrop))