package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock


private class WhenFlagClicked : BlockSpec(
    opcode = "event_whenflagclicked",
), Event, HatBlock


fun CommonScriptBuilder.whenFlagClicked() = addNode(WhenFlagClicked())



