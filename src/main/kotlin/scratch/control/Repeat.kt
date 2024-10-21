package me.jens.scratch.control

import me.jens.createTimes
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.Block
import scratch.createSubStack
import java.util.UUID

class Repeat(val times: Int, private vararg val childs: Node) : BlockSpecSpec(OpCode.control_repeat) {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {
        val name2 = name.toString()
        val newNext = nextUUID?.toString()

        val childUUIDS = childs.map { UUID.randomUUID() }
        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != childs.lastIndex

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                index = childIndex,
                childUUIDS[childIndex],
                nextUUID,
                layer+1
            )
        }

        val inputs = mutableMapOf("TIMES" to createTimes(times.toString()))
        if (childs.isNotEmpty()) {
            inputs["SUBSTACK"] = createSubStack(childUUIDS.first().toString())
        }

        visitors[name2] = BlockSpecSpec(
            opcode = OpCode.control_repeat,
            inputs = inputs
        ).toBlock(newNext, parent, layer == 0 && index == 0)

    }
}