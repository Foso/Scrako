package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class TurnLeft(private val reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnleft, reporterBlock),
    MotionBlock

fun ScriptBuilder.turnLeft(steps: ReporterBlock) = addNode(TurnLeft(steps))

fun ScriptBuilder.turnLeft(steps: Int) = addNode(TurnLeft(IntBlock(steps)))

