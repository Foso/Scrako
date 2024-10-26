package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.createSubStack
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.operator.BooleanBlock
import java.util.UUID

internal class IfThen(
    private val condition: BooleanBlock,
    private val leftStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val newNext = nextUUID
        val operatorUUID = UUID.randomUUID()
        val leftUUIDs = leftStack.map { UUID.randomUUID() }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_if,
            inputs = mapOf(
                "CONDITION" to createSubStack(operatorUUID.toString()),
                "SUBSTACK" to createSubStack(leftUUIDs.firstOrNull().toString())
            )
        ).toBlock(newNext, parent)
        condition.visit(visitors, identifier.toString(), operatorUUID, null, context)

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier.toString(),
                leftUUIDs[childIndex],
                nextUUID,
                context
            )
        }

    }
}


fun NodeBuilder.ifThen(
    block: BooleanBlock,
    leftStack: NodeBuilder.() -> Unit
) = addChild(IfThen(block, leftStack = NodeBuilder().apply(leftStack).childs))

