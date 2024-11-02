package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class SetVariable(private val variableName: String, private val item: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val variableId = context.variableMap[variableName]

        val itemUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_setvariableto",
            inputs = mapOf("VALUE" to setValue(item, itemUUID, context)),
            fields = mapOf("VARIABLE" to listOf(variableName, variableId?.toString()))
        ).toBlock(nextUUID, parent)
        item.visit(visitors, identifier, itemUUID, null, context)
    }
}

fun CommonScriptBuilder.setVariable(variable: ScratchVariable, item: ReporterBlock) =
    addNode(SetVariable(variable.name, item))

fun CommonScriptBuilder.setVariable(variable: ScratchVariable, newValue: Int) =
    addNode(SetVariable(variable.name, IntBlock(newValue)))

fun CommonScriptBuilder.setVariable(variable: ScratchVariable, item: String) =
    addNode(SetVariable(variable.name, StringBlock(item)))

