package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchVariable
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class SetVariable(private val variable: ScratchVariable, private val item: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val itemUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_setvariableto,
            inputs = mapOf("VALUE" to setValue(item, itemUUID)),
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID, parent)
        item.visit(visitors, identifier.toString(), itemUUID, null, context)
    }
}

fun NodeBuilder.setVariable(variable: ScratchVariable, item: ReporterBlock) = addChild(SetVariable(variable, item))

fun NodeBuilder.setVariable(variable: ScratchVariable, item: String) =
    addChild(SetVariable(variable, StringBlock(item)))

