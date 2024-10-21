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
        listIndex: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {

        if (nextUUID != null) {
            throw IllegalArgumentException("Forever block can't have a next block")
        }

        val blockMap = mutableMapOf<String, Block>()

        val childUUIDS = childs.map { UUID.randomUUID() }
        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                if (childIndex == childs.lastIndex) false else true

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                blockMap,
                parent = name.toString(),
                index = childIndex,
                listIndex,
                childUUIDS[childIndex],
                nextUUID,
                layer + 1
            )
        }

        val inputs: MutableMap<String, JsonArray> = mutableMapOf()

        if (blockMap.keys.isNotEmpty()) {
            inputs["SUBSTACK"] = createSubStack(blockMap.keys.first())
        }

        visitors[name.toString()] = BlockSpecSpec(
            opcode = OpCode.control_forever,
            inputs = inputs
        ).toBlock(null, parent, index == 0)

        visitors.putAll(blockMap)
    }
}


