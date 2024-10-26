package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ScratchVariable
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private open class MyVariable(private val variable: ScratchVariable, val opCode: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = opCode,
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID, identifier.toString())
    }
}

private class ShowVariable(variable: ScratchVariable) : MyVariable(variable, OpCode.data_showvariable)

fun NodeBuilder.hideVariable(variable: ScratchVariable) = addChild(HideVariable(variable))

private class HideVariable(variable: ScratchVariable) : MyVariable(variable, OpCode.data_hidevariable)

fun NodeBuilder.showVariable(variable: ScratchVariable) = addChild(ShowVariable(variable))
