package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.CBlock
import de.jensklingenberg.scrako.common.CapBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class Forever(private val childs: List<Node>) : Node, CapBlock, CBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {

        if (nextUUID != null) {
            throw IllegalArgumentException("Forever block can't have a next block")
        }

        val childUUIDS = childs.map { UUID.randomUUID().toString() }
        childs.mapIndexed { childIndex, visitor ->
            val nextchild = childIndex != childs.lastIndex

            val childNextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier,
                childUUIDS[childIndex],
                childNextUUID,
                context
            )
        }

        val inputs: MutableMap<String, JsonArray> = mutableMapOf()

        childUUIDS.firstOrNull()?.let {
            inputs["SUBSTACK"] = JsonArray(
                listOf(
                    JsonPrimitive(2),
                    JsonPrimitive(it)
                )
            )
        }

        visitors[identifier] = BlockSpec(
            opcode = "control_forever",
            inputs = inputs
        ).toBlock(null, parent)

    }
}

fun ScriptBuilder.forever(block: ScriptBuilder.() -> Unit) = addNode(Forever(ScriptBuilder().apply(block).childs))
