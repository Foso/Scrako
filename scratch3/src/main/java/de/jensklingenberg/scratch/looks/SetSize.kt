package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class SetSize(val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val operatorUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.looks_setsizeto,
            inputs = mapOf(
                "SIZE" to setValue(block, operatorUUID, context)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, operatorUUID, null, context)
    }
}

fun ScriptBuilder.setSize(block: ReporterBlock) = addNode(SetSize(block))
fun ScriptBuilder.setSize(block: Int): Unit = setSize(IntBlock(block))