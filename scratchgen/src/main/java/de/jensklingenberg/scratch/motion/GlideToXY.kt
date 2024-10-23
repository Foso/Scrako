package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ScratchType
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.operator.OperatorSpec

class GlideToXY(sec: String, toX: String, toY: String) : BlockSpec(
    opcode = OpCode.motion_glidesecstoxy,
    inputs = mapOf(
        "SECS" to createMessage(1, checkType(sec), sec),
        "X" to createMessage(1, ScratchType.NUMBER.value, toX),
        "Y" to createMessage(1, ScratchType.NUMBER.value, toY)
    )
), MotionBlock

fun checkType(data: Any): Int {

  return  when(data){
        is OperatorSpec->{
            ScratchType.BLOCKREF.value
        }

      else -> {
          ScratchType.STRING.value
      }
  }
}

fun NodeBuilder.glideToXY(sec: Double, toX: Double, toY: Double) =
    addChild(GlideToXY(sec.toString(), toX.toString(), toY.toString()))
