package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchVariable

private open class MyVariable(private val variable: String, val opCode: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val varId = context.variableMap[variable]
        visitors[identifier] = BlockSpec(
            opcode = opCode,
            fields = mapOf("VARIABLE" to listOf(variable, varId.toString()))
        ).toBlock(nextUUID, identifier)
    }
}

private class ShowVariable(variable: ScratchVariable) : MyVariable(variable.name, "data_showvariable")

fun ScriptBuilder.hideVariable(variable: ScratchVariable) = addNode(HideVariable(variable))

private class HideVariable(variable: ScratchVariable) : MyVariable(variable.name, "data_hidevariable")

fun ScriptBuilder.showVariable(variable: ScratchVariable) = addNode(ShowVariable(variable))
