package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class SetSize(val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_setsizeto,
            inputs = mapOf(
                "SIZE" to setValue(block, operatorUUID)
            )
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), operatorUUID, null, context)
    }
}

fun ScriptBuilder.setSize(block: ReporterBlock) = addChild(SetSize(block))
fun ScriptBuilder.setSize(block: Int) = setSize(IntBlock(block))