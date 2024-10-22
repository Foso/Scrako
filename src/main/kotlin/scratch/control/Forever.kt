package me.jens.scratch.control

import kotlinx.serialization.json.JsonArray
import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.CBlock
import me.jens.scratch.common.CapBlock
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.createSubStack
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
            inputs["SUBSTACK"] = createSubStack(it.toString())
        }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_forever,
            inputs = inputs
        ).toBlock(null, parent, index == 0)

    }
}


