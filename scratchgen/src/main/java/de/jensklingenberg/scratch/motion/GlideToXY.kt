package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchType
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
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
        ).toBlock(nextUUID, parent, context.topLevel)

        sec.visit(visitors, identifier.toString(), secID, null, context.copy(topLevel = false))
        toX.visit(visitors, identifier.toString(), toXID, null, context.copy(topLevel = false))
        toY.visit(visitors, identifier.toString(), toYID, null, context.copy(topLevel = false))
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

fun NodeBuilder.glideToXY(sec: ReporterBlock, toX: ReporterBlock, toY: ReporterBlock) =
    addChild(GlideToXY(sec, toX, toY))

fun NodeBuilder.glideToXY(sec: Double, toX: Double, toY: Double) =
    addChild(GlideToXY(DoubleBlock(sec), DoubleBlock(toX), DoubleBlock(toY)))
