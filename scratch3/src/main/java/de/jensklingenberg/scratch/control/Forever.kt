package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.CBlock
import de.jensklingenberg.scrako.common.CapBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class Forever(private val childs: List<Node>) : Node, CapBlock, CBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,
    ) {

        if (nextUUID != null) {
            throw IllegalArgumentException("Forever block can't have a next block")
        }

        val childUUIDS = childs.map { UUID.randomUUID() }
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

        val inputs: MutableMap<String, JsonArray> = mutableMapOf()

        childUUIDS.firstOrNull()?.let {
            inputs["SUBSTACK"] = JsonArray(
                listOf(
                    JsonPrimitive(2),
                    JsonPrimitive(it.toString())
                )
            )
        }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_forever,
            inputs = inputs
        ).toBlock(null, parent)

    }
}

fun ScriptBuilder.forever(block: ScriptBuilder.() -> Unit) {
    childs.add(Forever(ScriptBuilder().apply(block).childs))
}