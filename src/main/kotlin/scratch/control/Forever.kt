package me.jens.scratch.control

import kotlinx.serialization.json.JsonArray
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.CBlock
import me.jens.scratch.common.CapBlock
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.Block
import scratch.createSubStack
import java.util.UUID

class Forever(private vararg val childs: Node) : Node, CapBlock, CBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {

        if (nextUUID != null) {
            throw IllegalArgumentException("Forever block can't have a next block")
        }

        val childUUIDS = childs.map { UUID.randomUUID() }
        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                if (childIndex == childs.lastIndex) false else true

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name.toString(),
                index = childIndex,
                childUUIDS[childIndex],
                nextUUID,
                layer + 1
            )
        }

        val inputs: MutableMap<String, JsonArray> = mutableMapOf()

        if (childs.isNotEmpty()) {
            inputs["SUBSTACK"] = createSubStack(childUUIDS.first().toString())
        }

        visitors[name.toString()] = BlockSpecSpec(
            opcode = OpCode.control_forever,
            inputs = inputs
        ).toBlock(null, parent, index == 0)

    }
}


