package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock


private class WhenFlagClicked : BlockSpec(
    opcode = ("event_whenflagclicked"),
), Event, HatBlock


fun ScriptBuilder.whenFlagClicked() = addNode(WhenFlagClicked())




