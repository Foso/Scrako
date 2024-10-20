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

    override fun visit(visitors: MutableMap<String, Block>, layer: Int, parent: String?, index: Int, next: String?) {
        val name = "block$index$layer"

        val blockMap = mutableMapOf<String, Block>()

        operatorSpec.visit(blockMap, layer + 1, name, 0, null)

        val leftBlockMap = mutableMapOf<String, Block>()

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                if (childIndex == leftStack.lastIndex) null else "block${childIndex + 1}${layer + 1}left"
            visitor.visit(leftBlockMap, layer + 1, parent = name, index = childIndex, next = nextchild)
        }

        val rightBlockMap = mutableMapOf<String, Block>()

        rightStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                if (childIndex == rightStack.lastIndex) null else "block${childIndex + 1}${layer + 1}right"
            visitor.visit(rightBlockMap, layer + 1, parent = name, index = childIndex, next = nextchild)
        }

        visitors[name] = BlockSpecSpec(
            opcode = OpCode.control_forever,
            childBlocks = emptyList(),
            inputs = mapOf(
                "CONDITION" to createSubStack(blockMap.keys.first()),
                "SUBSTACK" to createSubStack(leftBlockMap.keys.first()),
                "SUBSTACK" to createSubStack(rightBlockMap.keys.first())
            )
        ).toBlock(next, parent, layer == 0 && index == 0)

        visitors.putAll(leftBlockMap)
        visitors.putAll(rightBlockMap)
        visitors.putAll(blockMap)
    }
}