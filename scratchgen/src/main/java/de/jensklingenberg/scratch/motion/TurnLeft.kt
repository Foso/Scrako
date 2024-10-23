package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

class TurnLeft(private val reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnleft, reporterBlock)

fun NodeBuilder.turnLeft(steps: ReporterBlock) = addChild(TurnLeft(steps))

fun NodeBuilder.turnLeft(steps: Int) = addChild(TurnLeft(IntBlock(steps)))

