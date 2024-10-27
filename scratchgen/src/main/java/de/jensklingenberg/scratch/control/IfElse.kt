package de.jensklingenberg.scratch.control


import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.createSubStack
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BooleanBlock
import java.util.UUID

internal class IfElse(
    private val condition: BooleanBlock,
    private val leftStack: List<Node>,
    private val rightStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        val name2 = identifier.toString()
        val newNext = nextUUID
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
        ).toBlock(newNext, parent)
        condition.visit(visitors, name2, operatorUUID, null, )

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                leftUUIDs[childIndex],
                nextUUID,
                
            )
        }

        rightStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != rightStack.lastIndex

            val nextUUID = if (nextchild) rightUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                rightUUIDs[childIndex],
                nextUUID,
                
            )
        }

    }
}

fun ScriptBuilder.ifElse(
    operatorSpec: BooleanBlock,
    leftStack: ScriptBuilder.() -> Unit,
    rightStack: ScriptBuilder.() -> Unit,
) = addChild(
    IfElse(
        operatorSpec,
        leftStack = ScriptBuilder().apply(leftStack).childs,
        rightStack = ScriptBuilder().apply(rightStack).childs
    )
)
