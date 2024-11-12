package de.jensklingenberg.scratch3.procedures

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.builder.StageScriptBuilder
import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.ArgumentType
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.BlockFull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class Definition(
    private val prototype: Prototype,
    private val childs: MutableList<Node>
) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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
                context
            )
        }

    }
}

interface Argument : ReporterBlock {
    val name: String
    val defaultValue: String
}


class DefinitionArgs(private val functionName: String, private val args2: List<Argument>) {

    fun getArgs() =
        args2.map {
            when (it.type) {
                ArgumentType.BOOLEAN -> addArgumentBoolean(it.name)
                ArgumentType.NUMBER_OR_TEXT -> addArgumentString(it.name)
            }
        }.ifEmpty { throw IllegalStateException("No arguments defined for: $functionName") }

}

fun SpriteScriptBuilder.define(
    customBlockName: String,
    arguments: List<Argument> = emptyList(),
    withoutRefresh: Boolean = false,
    builder: SpriteScriptBuilder.(DefinitionArgs) -> Unit
) {
    functionsMap[customBlockName] = arguments
    val def = DefinitionArgs(customBlockName, arguments)
    addNode(
        Definition(
            Prototype(customBlockName, withoutRefresh, arguments),
            SpriteScriptBuilder().apply { builder(def) }.childs
        )
    )
}

fun StageScriptBuilder.define(
    customBlockName: String,
    arguments: List<Argument> = emptyList(),
    withoutRefresh: Boolean = false,
    builder: StageScriptBuilder.(DefinitionArgs) -> Unit
) {
    functionsMap[customBlockName] = arguments
    val def = DefinitionArgs(customBlockName, arguments)
    addNode(
        Definition(
            Prototype(customBlockName, withoutRefresh, arguments),
            StageScriptBuilder().apply { builder(def) }.childs
        )
    )
}
