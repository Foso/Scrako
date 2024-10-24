package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.model.Mutation
import de.jensklingenberg.scratch.operator.GreaterThan
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

class Call(val name: String, val inputs: List<Input> = emptyList()) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {


        val arguments = inputs.map { it.argument }
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
        val test = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.procedures_call,
            shadow = true,

            mutation = Mutation(
                tagName = "mutation", proccode = proccode, warp = "false", argumentnames = "[$argumentNames]",
                argumentids = "[${inputs.map { it.id }.joinToString { "\"" + it.toString() + "\"" }}]",
            ),

            inputs = inputs.associate {
                it.id.toString() to JsonArray(
                    listOf(JsonPrimitive(1), JsonPrimitive(test.toString()))
                )
            }
        ).toBlock(nextUUID, parent, context.topLevel)


        GreaterThan(IntBlock(3), IntBlock(4)).visit(
            visitors,
            identifier.toString(),
            test,
            null,
            context.copy(topLevel = false)
        )
    }
}

fun NodeBuilder.call(name: String, arguments: List<Input>) = addChild(Call(name, arguments))