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

class Definition(private val prototypeName: String, private val withoutRefresh: Boolean, private val arguments : List<Argument> = emptyList()) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        val protoUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.procedures_definition,
            inputs = mapOf("custom_block" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(protoUUID.toString())))),
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)

        Prototype(this.prototypeName, withoutRefresh, arguments).visit(
            visitors,
            identifier.toString(),
            protoUUID,
            null,
            1,
            context.copy(topLevel = false)
        )
    }
}

private class Prototype(val name: String, private val withoutRefresh: Boolean, val arguments : List<Argument>) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        val argsIds = arguments.map { it.id }
        val inputIds = argsIds.map { UUID.randomUUID() }

        arguments.forEachIndexed { index, argument ->
            argument.visit(
                visitors,
                identifier.toString(),
                argsIds[index],
                argsIds.getOrNull(index + 1),
                layer + 1,
                context.copy(topLevel = false)
            )
        }

        val inputs = mutableMapOf<String, JsonArray>()
        arguments.forEachIndexed { index, _ ->
            inputs[inputIds[index].toString()] = JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(argsIds[index].toString())))
        }

        val proccode = this.name + " " + arguments.joinToString(" ") {
            when (it) {
                is ArgumentBoolean -> "%b"
                is ArgumentString -> "%s"
                else -> "%n"
            }
        }
        val argumentNames = arguments.joinToString(", ") {
           "\""+ it.name+"\""
        }

        val argumentDefaults = arguments.filter { it.defaultValue.isNotBlank() }.joinToString(", ") {
            "\""+ it.defaultValue+"\""
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
                argumentids = "[${inputIds.joinToString { "\""+it.toString()+"\"" }}]",
            )
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
    }
}

class ArgumentString(override val name: String, override val defaultValue: String = "") :  Argument {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.argument_reporter_string_number,
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, null, context.topLevel)
    }
}

interface Argument : Node, ReporterBlock{
    val name: String
    val defaultValue: String
    val id: UUID
        get() = UUID.randomUUID()
}

class ArgumentBoolean(override val name: String, override val defaultValue: String = "") : Argument {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.argument_reporter_boolean,
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, parent, context.topLevel)
    }
}

fun NodeBuilder.definition(prototypeName: String, withoutRefresh: Boolean = false, arguments : List<Argument> = emptyList()) =
    addChild(Definition(prototypeName, withoutRefresh, arguments))