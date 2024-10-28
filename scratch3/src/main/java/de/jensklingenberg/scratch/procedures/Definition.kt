package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.control.Forever
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class Definition(
    private val prototype: Prototype,
    private val childs: MutableList<Node>
) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val childUUIDS = childs.map { UUID.randomUUID() }

        val protoUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = "procedures_definition",
            inputs = mapOf("custom_block" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(protoUUID.toString())))),
        ).toBlock(childUUIDS.first(), null)

        prototype.visit(
            visitors,
            identifier.toString(),
            protoUUID,
            null,
            context
        )

        childs.mapIndexed { childIndex, visitor ->
            val nextchild = childIndex != childs.lastIndex

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier.toString(),
                childUUIDS[childIndex],
                nextUUID,
                context,

                )
        }

    }
}

class ArgumentString(override val name: String, override val defaultValue: String = "") : Argument {
    override val id: UUID = UUID.randomUUID()
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = "argument_reporter_string_number",
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, null)
    }
}

interface Argument : ReporterBlock {
    val name: String
    val defaultValue: String
    val id: UUID

}

data class Input(val argument: Argument) {
    val id: UUID = UUID.randomUUID()
}

sealed interface ArgumentType {
    data object BOOLEAN : ArgumentType
    data object STRING : ArgumentType
}

class Argument2(val name: String, type: ArgumentType)


class ArgumentBoolean(override val name: String, override val defaultValue: String = "") : Argument {
    override val id: UUID = UUID.randomUUID()

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.argument_reporter_boolean,
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, parent)
    }
}

fun ScriptBuilder.define(
    customBlockName: String,
    arguments: List<Input> = emptyList(),
    withoutRefresh: Boolean = false,
    block: ScriptBuilder.() -> Unit
) {

    addNode(
        Definition(
            Prototype(customBlockName, withoutRefresh, arguments),
            ScriptBuilder().apply(block).childs
        )
    )
}


fun ScriptBuilder.forever(block: ScriptBuilder.() -> Unit) {
    childs.add(Forever(ScriptBuilder().apply(block).childs))
}