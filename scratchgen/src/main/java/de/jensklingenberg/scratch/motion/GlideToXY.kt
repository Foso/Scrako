package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createMessage

class GlideToXY(val sec: String, val toX : String, val toY: String): BlockSpec(
    opcode = OpCode.motion_glidesecstoxy,
    inputs = mapOf(
        "SECS" to createMessage(1, 4, sec),
        "X" to createMessage(1, 4, toX),
        "Y" to createMessage(1, 4, toY)
    )
), MotionBlock

fun NodeBuilder.glideToXY(sec: Double, toX: Double, toY: Double) = addChild(GlideToXY(sec.toString(), toX.toString(), toY.toString()))
