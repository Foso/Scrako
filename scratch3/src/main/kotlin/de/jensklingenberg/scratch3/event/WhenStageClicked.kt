package de.jensklingenberg.scratch3.event

import de.jensklingenberg.scrako.builder.StageScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock

private class WhenStageClicked : BlockSpec(
    opcode = "event_whenstageclicked",
), Event, HatBlock

fun StageScriptBuilder.whenStageClicked() = addNode(WhenStageClicked())
