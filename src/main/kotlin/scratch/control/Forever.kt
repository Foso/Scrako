package me.jens.scratch.control

import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode
import scratch.Block
import scratch.Visitor
import scratch.createSubStack

class Forever(private val childs: List<Visitor>) : Visitor {

    override fun visit(visitors: MutableMap<String, Block>, layer: Int, parent: String?, index: Int, next: String?) {
        val name = "block$index$layer"

        val blockMap = mutableMapOf<String, Block>()

        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                if (childIndex == childs.lastIndex) null else "block${childIndex + 1}${layer + 1}"
            visitor.visit(blockMap, layer + 1, parent = name, index = childIndex, next = nextchild)
        }

        visitors[name] = BlockSpecSpec(
            opcode = OpCode.control_forever,
            childBlocks = emptyList(),
            inputs = mapOf("SUBSTACK" to createSubStack(blockMap.keys.first()))
        ).toBlock(next, parent, layer == 0 && index == 0)

        visitors.putAll(blockMap)
    }
}


