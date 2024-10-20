package me.jens.scratch.control

import me.jens.OperatorSpec
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode
import scratch.Block
import scratch.Visitor
import scratch.createSubStack

class IfElse(
    private val operatorSpec: OperatorSpec,
    private val leftStack: List<Visitor>,
    private val rightStack: List<Visitor>
) : Visitor {

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

        operatorSpec.visit(blockMap, layer + 1, name, 0, false, listIndex)

        val leftBlockMap = mutableMapOf<String, Block>()

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex
            visitor.visit(leftBlockMap, layer + 1, parent = name, index = childIndex, next = nextchild, listIndex)
        }

        val rightBlockMap = mutableMapOf<String, Block>()

        rightStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != rightStack.lastIndex
            visitor.visit(rightBlockMap, layer + 1, parent = name, index = childIndex, next = nextchild, listIndex)
        }

        visitors[name] = BlockSpecSpec(
            opcode = OpCode.control_if_else,
            childBlocks = emptyList(),
            inputs = mapOf(
                "CONDITION" to createSubStack(blockMap.keys.first()),
                "SUBSTACK" to createSubStack(leftBlockMap.keys.first()),
                "SUBSTACK" to createSubStack(rightBlockMap.keys.first())
            )
        ).toBlock(newNext, parent, layer == 0 && index == 0)

        visitors.putAll(leftBlockMap)
        visitors.putAll(rightBlockMap)
        visitors.putAll(blockMap)
    }
}