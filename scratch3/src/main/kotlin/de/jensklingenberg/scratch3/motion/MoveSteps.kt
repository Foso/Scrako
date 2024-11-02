package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class Move(val block: ReporterBlock) : Node, MotionBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
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

fun CommonScriptBuilder.move(steps: Int) = addNode(Move(IntBlock(steps)))
fun CommonScriptBuilder.move(steps: Double) = addNode(Move(DoubleBlock(steps)))
fun CommonScriptBuilder.move(steps: ReporterBlock) = addNode(Move(steps))


