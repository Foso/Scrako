package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Mutation
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import java.util.UUID

private class Call(val functionName: String, val block0: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        val arguments = listOf(Argument2("mybool", ArgumentType.NUMBER_OR_TEXT))

        val id = context.functions.first { it.functionName == functionName && it.name == "mybool" }.id
        val procodeArgs = arguments.joinToString {
            when (it.type) {
                ArgumentType.BOOLEAN -> "%b"
                ArgumentType.NUMBER_OR_TEXT -> "%s"
                // ArgumentType.NUMBER -> "%n"
            }
        }
        val argIds = context.functions.filter { it.functionName == functionName }.map { it.id }

        val withoutRefresh = false
        visitors[identifier] = BlockSpec(
            opcode = "procedures_call",
            inputs = mapOf(
                id to setValue(block0, block0Id, context)
            ),
            fields = mapOf(

            ),
            mutation = Mutation(
                tagName = "mutation",
                proccode = "$functionName $procodeArgs",
                warp = "$withoutRefresh",
                argumentids = "[${argIds.joinToString { "\"" + it + "\"" }}]",
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun ScriptBuilder.call(name: String, block0: ReporterBlock) = addNode(Call(name, block0))