package de.jensklingenberg.scratch.control


import de.jensklingenberg.scratch.common.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.createSubStack
import de.jensklingenberg.scratch.operator.OperatorSpec
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
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        val name2 = identifier.toString()
        val newNext = nextUUID?.toString()
        val operatorUUID = UUID.randomUUID()
        val leftUUIDs = leftStack.map { UUID.randomUUID() }
        val rightUUIDs = rightStack.map { UUID.randomUUID() }

        visitors[name2] = BlockSpec(
            opcode = OpCode.control_if_else,
            inputs = mapOf(
                "CONDITION" to createSubStack(operatorUUID.toString()),
                "SUBSTACK" to createSubStack(leftUUIDs.first().toString()),
                "SUBSTACK2" to createSubStack(rightUUIDs.first().toString())
            )
        ).toBlock(newNext, parent, layer == 0 && index == 0)
        operatorSpec.visit(visitors, name2, 0, operatorUUID, null, layer + 1, context)

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