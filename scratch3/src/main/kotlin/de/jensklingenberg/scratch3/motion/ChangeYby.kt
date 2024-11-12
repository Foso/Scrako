package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class ChangeYby(val block: ReporterBlock) : ReporterBlock, MotionBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val operatorUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "motion_changeyby",
            inputs = mapOf(
                "DY" to setValue(block, operatorUUID, context)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, operatorUUID, null, context)
    }
}

fun SpriteScriptBuilder.changeYby(value: Int) = addNode(ChangeYby(IntBlock(value)))
fun SpriteScriptBuilder.changeYby(block: ReporterBlock) = addNode(ChangeYby(block))