package me.jens.scratch.control

import me.jens.createTimes
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode
import scratch.Block
import scratch.createSubStack

class Repeat(val times: Int, private val childs: List<BlockSpecSpec>) : BlockSpecSpec(OpCode.control_repeat) {
    override fun visit(
        visitors: MutableMap<String, Block>,
        layer: Int,
        parent: String?,
        index: Int,
        next: Boolean,
        listIndex: Int
    ) {
        val name = "${listIndex}_${layer}_$index"
        val newNext = if (!next) null else "${listIndex}_${layer}_${index + 1}"
        val blockMap = mutableMapOf<String, Block>()

        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != childs.lastIndex
            visitor.visit(blockMap, layer + 1, parent = name, index = childIndex, next = nextchild,listIndex)
        }

        visitors[name] = BlockSpecSpec(
            opcode = OpCode.control_repeat,
            childBlocks = emptyList(),
            inputs = mapOf(
                "TIMES" to createTimes(times.toString()),
                "SUBSTACK" to createSubStack(blockMap.keys.first())
            )
        ).toBlock(newNext, parent, layer == 0 && index == 0)

        visitors.putAll(blockMap)
    }
}