package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import java.util.UUID

private class Wait(private val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val uuid = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "control_wait",
            inputs = mapOf(
                "DURATION" to setValue(block, uuid, context)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, uuid, null, context)
    }
}

fun ScriptBuilder.wait(seconds: Double) = addNode(Wait(DoubleBlock(seconds)))
fun ScriptBuilder.wait(seconds: Int) = addNode(Wait(IntBlock(seconds)))
fun ScriptBuilder.wait(block: ReporterBlock) = addNode(Wait(block))

