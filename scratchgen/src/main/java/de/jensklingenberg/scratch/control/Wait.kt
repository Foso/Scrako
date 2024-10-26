package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class Wait(private val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.ControlWait,
            inputs = mapOf(
                "DURATION" to setValue(block, uuid)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), uuid, null, context.copy(topLevel = false))
    }
}

fun NodeBuilder.wait(seconds: Double) = addChild(Wait(DoubleBlock(seconds)))
fun NodeBuilder.wait(seconds: Int) = addChild(Wait(IntBlock(seconds)))
fun NodeBuilder.wait(block: ReporterBlock) = addChild(Wait(block))

