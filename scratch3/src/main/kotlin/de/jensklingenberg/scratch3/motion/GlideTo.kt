package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class GlideTo(val sec: ReporterBlock, val toX: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val secID = UUID.randomUUID().toString()
        val toXID = UUID.randomUUID().toString()

        visitors[identifier] = BlockSpec(
            opcode = "motion_glideto",
            inputs = mapOf(
                "SECS" to setValue(sec, secID, context),
                "X" to setValue(toX, toXID, context)
            )
        ).toBlock(nextUUID, parent)

        sec.visit(visitors, identifier, secID, null, context)
        // toX.visit(visitors, identifier, toXID, null, context)

    }
}

//https://en.scratch-wiki.info/wiki/Glide_()_Secs_to_()_(block)
fun SpriteScriptBuilder.glideTo(sec: ReporterBlock, toX: ReporterBlock) =
    addNode(GlideTo(sec, toX))
