package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

private class TurnRight(reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnright, reporterBlock)

fun ScriptBuilder.turnRight(block: ReporterBlock) = addChild(TurnRight(block))

fun ScriptBuilder.turnRight(steps: Int) = addChild(IntBlock(steps))