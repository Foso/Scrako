package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class Move(val block: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val childId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "motion_movesteps",
            inputs = mapOf("STEPS" to setValue(block, childId, context))
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, childId, null, context)

    }
}

fun SpriteScriptBuilder.move(steps: Int) = addNode(Move(IntBlock(steps)))
fun SpriteScriptBuilder.move(steps: Double) = addNode(Move(DoubleBlock(steps)))
fun SpriteScriptBuilder.move(steps: ReporterBlock) = addNode(Move(steps))


