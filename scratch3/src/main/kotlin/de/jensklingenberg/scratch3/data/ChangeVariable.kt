package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class ChangeVariable(private val block: ReporterBlock, private val variableName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val variable = context.variableMap[variableName]
        val itemUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_changevariableby",
            inputs = mapOf("VALUE" to setValue(block, itemUUID, context)),
            fields = mapOf("VARIABLE" to listOf(variableName, variable?.toString()))
        ).toBlock(nextUUID, identifier)
        block.visit(visitors, identifier, itemUUID, null, context)
    }
}


fun CommonScriptBuilder.changeVariableBy(variable: ScratchVariable, item: ReporterBlock) =
    addNode(ChangeVariable(item, variable.name))

fun CommonScriptBuilder.changeVariableBy(variable: ScratchVariable, by: Int) =
    addNode(ChangeVariable(IntBlock(by), variable.name))

fun CommonScriptBuilder.changeVariableBy(variable: ScratchVariable, item: Double) =
    addNode(ChangeVariable(DoubleBlock(item), variable.name))