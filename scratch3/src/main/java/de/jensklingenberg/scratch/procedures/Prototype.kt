package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Mutation
import de.jensklingenberg.scrako.common.Node
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class Prototype(val name: String, private val withoutRefresh: Boolean, val inputs: List<Input>) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
    ) {
        val inputIds = inputs.map { it.id }
        val arguments = inputs.map { it.argument }
        val argIds = arguments.map { it.id }
        arguments.forEachIndexed { index, argument ->
            argument.visit(
                visitors,
                identifier.toString(),
                argIds[index],
                argIds.getOrNull(index + 1), context,

                )
        }

        val inputs = mutableMapOf<String, JsonArray>()
        this.inputs.map { it.argument }.forEachIndexed { index, argument ->
            inputs[inputIds[index].toString()] = when (argument) {
                is ArgumentBoolean -> JsonArray(
                    listOf(JsonPrimitive(1), JsonPrimitive(argIds[index].toString()))
                )

                is ArgumentString -> JsonArray(
                    listOf(JsonPrimitive(1), JsonPrimitive(argIds[index].toString()))
                )

                else -> throw IllegalArgumentException("Unknown argument type")

            }
        }

        val proccode = this.name + "" + arguments.joinToString(" ") {
            when (it) {
                is ArgumentBoolean -> "%b"
                is ArgumentString -> "%s"
                else -> "%n"
            }
        }
        val argumentNames = arguments.joinToString(", ") {
            "\"" + it.name + "\""
        }

        val argumentDefaults = arguments.filter { it.defaultValue.isNotBlank() }.joinToString(", ") {
            "\"" + it.defaultValue + "\""
        }


        visitors[identifier.toString()] = BlockSpec(
            opcode = "procedures_prototype",
            shadow = true,
            inputs = inputs,
            mutation = Mutation(
                tagName = "mutation",
                proccode = proccode,
                warp = "$withoutRefresh",
                argumentnames = "[$argumentNames]",
                argumentids = "[${argIds.joinToString { "\"" + it.toString() + "\"" }}]",
            )
        ).toBlock(nextUUID, parent)
    }
}