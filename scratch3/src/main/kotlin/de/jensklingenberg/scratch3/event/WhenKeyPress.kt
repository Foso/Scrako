package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenKeyPress(key: Key) : BlockSpec(
    opcode = "event_whenkeypressed",
    fields = mapOf("KEY_OPTION" to listOf(key.value, null))
), Event, HatBlock

fun CommonScriptBuilder.whenKeyPress(key: Key) = addNode(WhenKeyPress(key))

