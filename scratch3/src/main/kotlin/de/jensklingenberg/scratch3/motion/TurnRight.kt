package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

private class TurnRight(reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnright, reporterBlock), MotionBlock

fun ScriptBuilder.turnRight(block: ReporterBlock) = addNode(TurnRight(block))

fun ScriptBuilder.turnRight(steps: Int) = addNode(IntBlock(steps))