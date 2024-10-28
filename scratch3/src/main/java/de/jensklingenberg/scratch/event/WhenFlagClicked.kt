package de.jensklingenberg.scratch.event

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Event
import de.jensklingenberg.scrako.common.HatBlock
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode


private class WhenFlagClicked : BlockSpec(
    opcode = OpCode.Whenflagclicked,
), Event, HatBlock


fun ScriptBuilder.whenFlagClicked() = addChild(WhenFlagClicked())




