package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scratch.operator.Operator
import java.util.UUID

private class GlideToXY(val sec: ReporterBlock, val toX: ReporterBlock, val toY: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val secID = UUID.randomUUID()
        val toXID = UUID.randomUUID()
        val toYID = UUID.randomUUID()

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_glidesecstoxy,
            inputs = mapOf(
                "SECS" to setValue(sec, secID),
                "X" to setValue(toX, toXID),
                "Y" to setValue(toY, toYID)
            )
        ).toBlock(nextUUID, parent)

        sec.visit(visitors, identifier.toString(), secID, null, context)
        toX.visit(visitors, identifier.toString(), toXID, null, context)
        toY.visit(visitors, identifier.toString(), toYID, null, context)
    }
}


private fun checkType(data: Any): Int {

    return when (data) {
        is Operator -> {
            ScratchType.BLOCKREF.value
        }

        else -> {
            ScratchType.STRING.value
        }
    }
}

fun ScriptBuilder.glideToXY(sec: ReporterBlock, toX: ReporterBlock, toY: ReporterBlock) =
    addChild(GlideToXY(sec, toX, toY))

fun ScriptBuilder.glideToXY(sec: Double, toX: Double, toY: Double) =
    addChild(GlideToXY(DoubleBlock(sec), DoubleBlock(toX), DoubleBlock(toY)))
