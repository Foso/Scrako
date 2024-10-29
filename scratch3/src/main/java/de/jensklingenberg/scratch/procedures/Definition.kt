package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
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
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val childUUIDS = childs.map { UUID.randomUUID().toString() }

        val protoUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "procedures_definition",
            inputs = mapOf("custom_block" to JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(protoUUID)))),
        ).toBlock(childUUIDS.first(), null)

        prototype.visit(
            visitors,
            identifier,
            protoUUID,
            null,
            context
        )

        childs.mapIndexed { childIndex, visitor ->
            val nextchild = childIndex != childs.lastIndex

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier,
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
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "argument_reporter_string_number",
            fields = mapOf("VALUE" to listOf("number or text", null))
        ).toBlock(null, null)
    }
}

interface Argument : ReporterBlock {
    val name: String
    val defaultValue: String
    val id: UUID

}


sealed interface ArgumentType {
    data object BOOLEAN : ArgumentType
    data object NUMBER_OR_TEXT : ArgumentType
}

class Argument2(val name: String, val type: ArgumentType)


fun ScriptBuilder.define(
    customBlockName: String,
    arguments: List<Argument2> = emptyList(),
    withoutRefresh: Boolean = false,
    builder: ScriptBuilder.() -> Unit
) {
    functionsMap[customBlockName] = arguments.map { it.name }
    addNode(
        Definition(
            Prototype(customBlockName, withoutRefresh, arguments),
            ScriptBuilder().apply(builder).childs
        )
    )
}

