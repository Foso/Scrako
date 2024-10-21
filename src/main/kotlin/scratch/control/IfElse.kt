package me.jens.scratch.control

import me.jens.OperatorSpec
import me.jens.scratch.Block
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.createSubStack
import java.util.UUID

class IfElse(
    private val operatorSpec: OperatorSpec,
    private val leftStack: List<Node>,
    private val rightStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        val name2 = name.toString()
        val newNext = nextUUID?.toString()
        val blockMap = UUID.randomUUID()
        val leftUUIDs = leftStack.map { UUID.randomUUID() }
        val rightUUIDs = rightStack.map { UUID.randomUUID() }

        visitors[name2] = BlockSpecSpec(
            opcode = OpCode.control_if_else,
            inputs = mapOf(
                "CONDITION" to createSubStack(blockMap.toString()),
                "SUBSTACK" to createSubStack(leftUUIDs.first().toString()),
                "SUBSTACK2" to createSubStack(rightUUIDs.first().toString())
            )
        ).toBlock(newNext, parent, layer == 0 && index == 0)
        operatorSpec.visit(visitors, name2, 0, blockMap, null, layer + 1, context)

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                index = childIndex,
                leftUUIDs[childIndex],
                nextUUID,
                layer + 1,
                context
            )
        }

        rightStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != rightStack.lastIndex

            val nextUUID = if (nextchild) rightUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                index = childIndex,
                rightUUIDs[childIndex],
                nextUUID,
                layer + 1,
                context
            )
        }

    }
}