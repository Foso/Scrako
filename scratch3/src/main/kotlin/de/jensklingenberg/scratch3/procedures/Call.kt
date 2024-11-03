package de.jensklingenberg.scratch3.procedures

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.ArgumentType
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Mutation
import java.util.UUID

private class Call(val functionName: String, val blockList: List<ReporterBlock>) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val blockIds = blockList.map { UUID.randomUUID().toString() }
        val arguments = context.functions.filter { it.functionName == functionName }

        val inputMap = arguments.mapIndexed { index, argumenti ->
            try {
                argumenti.id to setValue(blockList[index], blockIds[index], context)
            } catch (e: IndexOutOfBoundsException) {
                throw IllegalStateException("Function $functionName missing argument ${argumenti.name} of type ${argumenti.type}")
            }
        }.toMap()

        val procodeArgs = arguments.joinToString {
            when (it.type) {
                ArgumentType.BOOLEAN -> "%b"
                ArgumentType.NUMBER_OR_TEXT -> "%s"
            }
        }
        val argIds = context.functions.filter { it.functionName == functionName }.map { it.id }

        visitors[identifier] = BlockSpec(
            opcode = "procedures_call",
            inputs = inputMap,
            mutation = Mutation(
                tagName = "mutation",
                proccode = "$functionName $procodeArgs",
                warp = "false",
                argumentids = "[${argIds.joinToString { "\"" + it + "\"" }}]",
            )
        ).toBlock(nextUUID, parent)
        blockList.forEachIndexed { index, reporterBlock ->
            reporterBlock.visit(visitors, identifier, blockIds[index], null, context)
        }
    }
}

fun CommonScriptBuilder.call(name: String, block0: List<ReporterBlock> = emptyList(),) = addNode(Call(name, block0))