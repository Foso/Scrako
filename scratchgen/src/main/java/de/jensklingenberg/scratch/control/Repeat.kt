package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.createSubStack
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class Repeat(private val times: ReporterBlock, private vararg val childs: Node) :
    BlockSpec(OpCode.control_repeat) {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID()

        val childUUIDS = childs.map { UUID.randomUUID() }

        val inputs = mutableMapOf(
            "TIMES" to setValue(times, operatorUUID)
        )

        childs.firstOrNull()?.let {
            inputs["SUBSTACK"] = createSubStack(childUUIDS.first().toString())
        }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_repeat,
            inputs = inputs
        ).toBlock(nextUUID, parent, context.topLevel)

        times.visit(
            visitors,
            identifier.toString(),
            operatorUUID,
            null,
            context.copy(topLevel = false)
        )

        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != childs.lastIndex

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier.toString(),
                childUUIDS[childIndex],
                nextUUID,
                context.copy(topLevel = false)
            )
        }


    }
}

fun NodeBuilder.repeat(times: Int, childs: NodeBuilder.() -> Unit) = repeat(IntBlock(times), childs)
fun NodeBuilder.repeat(times: Double, childs: NodeBuilder.() -> Unit) = repeat(DoubleBlock(times), childs)

fun NodeBuilder.repeat(times: ReporterBlock, childs: NodeBuilder.() -> Unit) =
    addChild(Repeat(times, *NodeBuilder().apply(childs).childs.toTypedArray()))