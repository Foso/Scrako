package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Mutation
import de.jensklingenberg.scrako.common.Node
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID


internal class Prototype(
    val customBlockName: String,
    val withoutRefresh: Boolean = false,
    val arguments: List<Argument2>
) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val blockIds = arguments.map { UUID.randomUUID().toString() }
        val procodeArgs = arguments.joinToString {
            when (it.type) {
                ArgumentType.BOOLEAN -> "%b"
                ArgumentType.NUMBER_OR_TEXT -> "%s"
                // ArgumentType.NUMBER -> "%n"
            }
        }

        val inputMap = arguments.mapIndexed { index, argument ->

            val uuid = context.functions.first { it.functionName == customBlockName && it.name == argument.name }.id
            Pair(
                uuid,
                JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(blockIds[index])))
            )
        }.associate {
            it.first to it.second
        }

        val argumentNames = arguments.joinToString {
            when (it.type) {
                ArgumentType.BOOLEAN -> "\"boolean\""
                ArgumentType.NUMBER_OR_TEXT -> "\"number or text\""
                // ArgumentType.NUMBER -> "%n"
            }
        }

        val argIds = context.functions.filter { it.functionName == customBlockName }.map { it.id }
        visitors[identifier] = BlockSpec(
            opcode = "procedures_prototype",
            inputs = inputMap,
            fields = mapOf(),
            mutation = Mutation(
                tagName = "mutation",
                proccode = customBlockName + " " + procodeArgs,
                warp = "$withoutRefresh",
                argumentnames = "[$argumentNames]",
                argumentids = "[${argIds.joinToString { "\"" + it + "\"" }}]",
            )
        ).toBlock(nextUUID, parent)

        arguments.forEach {
            when (it.type) {
                ArgumentType.BOOLEAN -> {
                    ArgumentBoolean(it.name).visit(
                        visitors,
                        identifier,
                        blockIds[arguments.indexOf(it)],
                        null,
                        context
                    )
                }

                ArgumentType.NUMBER_OR_TEXT -> {
                    ArgumentString(it.name).visit(
                        visitors,
                        identifier,
                        blockIds[arguments.indexOf(it)],
                        null,
                        context
                    )
                }
            }
        }

    }
}