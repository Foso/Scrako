package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock

private class TurnRight(reporterBlock: ReporterBlock) : Turn("motion_turnright", reporterBlock), MotionBlock

fun SpriteScriptBuilder.turnRight(block: ReporterBlock) = addNode(TurnRight(block))

fun SpriteScriptBuilder.turnRight(steps: Int) = addNode(IntBlock(steps))