package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class ChangeVariable(private val item: ReporterBlock, private val variableName: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context, ) {
        val variable = context.variables.find { it.name == variableName }!!
        val itemUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_changevariableby,
            inputs = mapOf("VALUE" to setValue(item, itemUUID)),
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID, identifier.toString())
        item.visit(visitors, identifier.toString(), itemUUID, null, context)
    }
}


fun ScriptBuilder.changeVariable(variable: ScratchVariable, item: ReporterBlock) =
    addChild(ChangeVariable(item, variable.name))

fun ScriptBuilder.changeVariable(variable: ScratchVariable, item: Int) =
    addChild(ChangeVariable(IntBlock(item), variable.name))

fun ScriptBuilder.changeVariable(variable: ScratchVariable, item: Double) =
    addChild(ChangeVariable(DoubleBlock(item), variable.name))