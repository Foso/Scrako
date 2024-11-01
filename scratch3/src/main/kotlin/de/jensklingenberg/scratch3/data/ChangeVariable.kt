package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch3.common.OpCode
import java.util.UUID

private class ChangeVariable(private val block: ReporterBlock, private val variableName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val variable = context.variableMap[variableName]
        val itemUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.data_changevariableby,
            inputs = mapOf("VALUE" to setValue(block, itemUUID, context)),
            fields = mapOf("VARIABLE" to listOf(variableName, variable?.toString()))
        ).toBlock(nextUUID, identifier)
        block.visit(visitors, identifier, itemUUID, null, context)
    }
}


fun ScriptBuilder.changeVariableBy(variable: ScratchVariable, item: ReporterBlock) =
    addNode(ChangeVariable(item, variable.name))

fun ScriptBuilder.changeVariableBy(variable: ScratchVariable, by: Int) =
    addNode(ChangeVariable(IntBlock(by), variable.name))

fun ScriptBuilder.changeVariableBy(variable: ScratchVariable, item: Double) =
    addNode(ChangeVariable(DoubleBlock(item), variable.name))