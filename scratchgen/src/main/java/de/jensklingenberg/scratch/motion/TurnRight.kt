package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

class TurnRight(private val reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnright, reporterBlock)


fun NodeBuilder.turnRight(block: ReporterBlock) = addChild(TurnRight(block))

fun NodeBuilder.turnRight(steps: Int) = addChild(IntBlock(steps))