package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.model.Mutation
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

class Definition(
    private val prototypeName: String,
    private val withoutRefresh: Boolean,
    private val inputs: List<Input> = emptyList()
) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val protoUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.procedures_definition,
            inputs = mapOf("custom_block" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(protoUUID.toString())))),
        ).toBlock(nextUUID, parent, context.topLevel)

        Prototype(this.prototypeName, withoutRefresh, inputs).visit(
            visitors,
            identifier.toString(),
            protoUUID,
            null,
            context.copy(topLevel = false)
        )
    }
}

private class Prototype(val name: String, private val withoutRefresh: Boolean, val inputs: List<Input>) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val inputIds = inputs.map { it.id }
        val arguments = inputs.map { it.argument }
        val argIds = arguments.map { it.id }
        arguments.forEachIndexed { index, argument ->
            argument.visit(
                visitors,
                identifier.toString(),
                argIds[index],
                argIds.getOrNull(index + 1),
                context.copy(topLevel = false)
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

        val proccode = this.name + " " + arguments.joinToString(" ") {
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
            opcode = OpCode.procedures_prototype,
            shadow = true,
            inputs = inputs,
            mutation = Mutation(
                tagName = "mutation",
                proccode = proccode,
                warp = "$withoutRefresh",
                argumentnames = "[$argumentNames]",
                argumentids = "[${argIds.joinToString { "\"" + it.toString() + "\"" }}]",
            )
        ).toBlock(nextUUID, parent, context.topLevel)
    }
}

class ArgumentString(override val name: String, override val defaultValue: String = "") : Argument {
    override val id: UUID = UUID.randomUUID()
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.argument_reporter_string_number,
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, null, context.topLevel)
    }
}

interface Argument : Node, ReporterBlock {
    val name: String
    val defaultValue: String
    val id: UUID

}

data class Input(val argument: Argument) {
    val id: UUID = UUID.randomUUID()
}

class ArgumentBoolean(override val name: String, override val defaultValue: String = "") : Argument {
    override val id: UUID = UUID.randomUUID()

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.argument_reporter_boolean,
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, parent, context.topLevel)
    }
}

fun NodeBuilder.definition(
    prototypeName: String,
    withoutRefresh: Boolean = false,
    arguments: List<Input> = emptyList()
) =
    addChild(Definition(prototypeName, withoutRefresh, arguments))