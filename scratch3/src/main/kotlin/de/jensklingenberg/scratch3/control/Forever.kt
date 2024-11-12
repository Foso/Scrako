package de.jensklingenberg.scratch3.control

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.CBlock
import de.jensklingenberg.scrako.common.CapBlock
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.builder.StageScriptBuilder
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class Forever(private val childs: List<Node>) : Node, CapBlock, CBlock {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {

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

fun StageScriptBuilder.forever(block: CommonScriptBuilder.() -> Unit) = addNode(Forever(StageScriptBuilder().apply(block).childs))
fun SpriteScriptBuilder.forever(block: CommonScriptBuilder.() -> Unit) = addNode(Forever(SpriteScriptBuilder().apply(block).childs))

