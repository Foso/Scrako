package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class SetVariable(private val variableName: String, private val item: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
    ) {
        val variableId = context.variableMap[variableName]

        val itemUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_setvariableto,
            inputs = mapOf("VALUE" to setValue(item, itemUUID, context)),
            fields = mapOf("VARIABLE" to listOf(variableName, variableId?.toString()))
        ).toBlock(nextUUID, parent)
        item.visit(visitors, identifier.toString(), itemUUID, null, context)
    }
}

fun ScriptBuilder.setVariable(variable: ScratchVariable, item: ReporterBlock) =
    addNode(SetVariable(variable.name, item))

fun ScriptBuilder.setVariable(variable: ScratchVariable, item: String) =
    addNode(SetVariable(variable.name, StringBlock(item)))

