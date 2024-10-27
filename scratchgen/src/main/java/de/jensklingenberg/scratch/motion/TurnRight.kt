package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class TurnRight(reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnright, reporterBlock)

fun ScriptBuilder.turnRight(block: ReporterBlock) = addChild(TurnRight(block))

fun ScriptBuilder.turnRight(steps: Int) = addChild(IntBlock(steps))