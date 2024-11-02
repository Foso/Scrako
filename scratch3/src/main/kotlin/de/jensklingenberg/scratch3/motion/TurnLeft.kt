package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

private class TurnLeft(private val reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnleft, reporterBlock),
    MotionBlock

fun CommonScriptBuilder.turnLeft(steps: ReporterBlock) = addNode(TurnLeft(steps))

fun CommonScriptBuilder.turnLeft(steps: Int) = addNode(TurnLeft(IntBlock(steps)))

