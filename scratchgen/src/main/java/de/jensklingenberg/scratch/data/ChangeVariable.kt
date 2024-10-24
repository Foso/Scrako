package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchVariable
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ChangeVariable(private val variable: ScratchVariable, private val item: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val itemUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_changevariableby,
            inputs = mapOf("VALUE" to setValue(item, itemUUID)),
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID, identifier.toString(), context.topLevel)
        item.visit(visitors, identifier.toString(), itemUUID, null, context.copy(topLevel = false))
    }
}


fun NodeBuilder.changeVariable(variable: ScratchVariable, item: ReporterBlock) =
    addChild(ChangeVariable(variable, item))

fun NodeBuilder.changeVariable(variable: ScratchVariable, item: Int) =
    addChild(ChangeVariable(variable, IntBlock(item)))

fun NodeBuilder.changeVariable(variable: ScratchVariable, item: Double) =
    addChild(ChangeVariable(variable, DoubleBlock(item)))