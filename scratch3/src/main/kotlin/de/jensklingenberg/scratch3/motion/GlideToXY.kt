package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class GlideToXY(val sec: ReporterBlock, val toX: ReporterBlock, val toY: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val secID = UUID.randomUUID().toString()
        val toXID = UUID.randomUUID().toString()
        val toYID = UUID.randomUUID().toString()

        visitors[identifier] = BlockSpec(
            opcode = "motion_glidesecstoxy",
            inputs = mapOf(
                "SECS" to setValue(sec, secID, context),
                "X" to setValue(toX, toXID, context),
                "Y" to setValue(toY, toYID, context)
            )
        ).toBlock(nextUUID, parent)

        sec.visit(visitors, identifier, secID, null, context)
        toX.visit(visitors, identifier, toXID, null, context)
        toY.visit(visitors, identifier, toYID, null, context)
    }
}


fun SpriteScriptBuilder.glideToXY(sec: ReporterBlock, toX: ReporterBlock, toY: ReporterBlock) =
    addNode(GlideToXY(sec, toX, toY))

fun SpriteScriptBuilder.glideToXY(sec: Double, toX: Double, toY: Double) =
    addNode(GlideToXY(DoubleBlock(sec), DoubleBlock(toX), DoubleBlock(toY)))
