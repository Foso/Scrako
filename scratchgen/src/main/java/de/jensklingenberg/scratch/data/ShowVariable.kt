package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.Block
import java.util.UUID

private open class MyVariable(private val variable: ScratchVariable, val opCode: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = opCode,
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID, identifier.toString())
    }
}

private class ShowVariable(variable: ScratchVariable) : MyVariable(variable, OpCode.data_showvariable)

fun ScriptBuilder.hideVariable(variable: ScratchVariable) = addChild(HideVariable(variable))

private class HideVariable(variable: ScratchVariable) : MyVariable(variable, OpCode.data_hidevariable)

fun ScriptBuilder.showVariable(variable: ScratchVariable) = addChild(ShowVariable(variable))
