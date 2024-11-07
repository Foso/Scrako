package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.model.BlockFull

private open class MyVariable(private val variable: String, val opCode: String) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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

fun CommonScriptBuilder.hideVariable(variable: ScratchVariable) = addNode(HideVariable(variable))

private class HideVariable(variable: ScratchVariable) : MyVariable(variable.name, "data_hidevariable")

fun CommonScriptBuilder.showVariable(variable: ScratchVariable) = addNode(ShowVariable(variable))
