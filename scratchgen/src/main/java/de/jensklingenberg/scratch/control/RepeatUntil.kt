package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.createSubStack
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BooleanBlock
import java.util.UUID

internal class RepeatUntil(
    private val condition: BooleanBlock,
    private val leftStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        val operatorUUID = UUID.randomUUID()
        val leftUUIDs = leftStack.map { UUID.randomUUID() }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_repeat_until,
            inputs = mapOf(
                "CONDITION" to createSubStack(operatorUUID.toString()),
                "SUBSTACK" to createSubStack(leftUUIDs.firstOrNull().toString())
            )
        ).toBlock(nextUUID, parent)
        condition.visit(visitors, identifier.toString(), operatorUUID, null, )

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier.toString(),
                leftUUIDs[childIndex],
                nextUUID,
                
            )
        }

    }
}

fun ScriptBuilder.repeatUntil(
    block: BooleanBlock,
    leftStack: ScriptBuilder.() -> Unit
) = addChild(RepeatUntil(block, leftStack = ScriptBuilder().apply(leftStack).childs))