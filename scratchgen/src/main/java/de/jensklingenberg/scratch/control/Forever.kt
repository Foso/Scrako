package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.CBlock
import de.jensklingenberg.scratch.common.CapBlock
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import java.util.UUID

class Forever(private vararg val childs: Node) : Node, CapBlock, CBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
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
                index = childIndex,
                childUUIDS[childIndex],
                nextUUID,
                layer + 1,
                context
            )
        }

        val inputs: MutableMap<String, JsonArray> = mutableMapOf()

        childUUIDS.firstOrNull()?.let {
            inputs["SUBSTACK"] = de.jensklingenberg.scratch.createSubStack(it.toString())
        }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_forever,
            inputs = inputs
        ).toBlock(null, parent, index == 0)

    }
}


