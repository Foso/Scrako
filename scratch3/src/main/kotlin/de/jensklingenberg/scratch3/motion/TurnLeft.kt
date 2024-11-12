package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock

private class TurnLeft(reporterBlock: ReporterBlock) : Turn("motion_turnleft", reporterBlock),
    MotionBlock

fun SpriteScriptBuilder.turnLeft(steps: ReporterBlock) = addNode(TurnLeft(steps))

fun SpriteScriptBuilder.turnLeft(steps: Int) = addNode(TurnLeft(IntBlock(steps)))

