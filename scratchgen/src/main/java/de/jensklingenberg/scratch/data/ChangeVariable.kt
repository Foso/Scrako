package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class ChangeVariable(private val variable: ScratchVariable, private val item: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
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
    addChild(ChangeVariable(variable, item))

fun ScriptBuilder.changeVariable(variable: ScratchVariable, item: Int) =
    addChild(ChangeVariable(variable, IntBlock(item)))

fun ScriptBuilder.changeVariable(variable: ScratchVariable, item: Double) =
    addChild(ChangeVariable(variable, DoubleBlock(item)))