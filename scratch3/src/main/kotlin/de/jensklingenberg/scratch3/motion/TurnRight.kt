package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock

private class TurnRight(reporterBlock: ReporterBlock) : Turn("motion_turnright", reporterBlock), MotionBlock

fun CommonScriptBuilder.turnRight(block: ReporterBlock) = addNode(TurnRight(block))

fun CommonScriptBuilder.turnRight(steps: Int) = addNode(IntBlock(steps))