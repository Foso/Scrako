package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock

private class TurnLeft(private val reporterBlock: ReporterBlock) : Turn(OpCode.motion_turnleft, reporterBlock)

fun ScriptBuilder.turnLeft(steps: ReporterBlock) = addChild(TurnLeft(steps))

fun ScriptBuilder.turnLeft(steps: Int) = addChild(TurnLeft(IntBlock(steps)))

