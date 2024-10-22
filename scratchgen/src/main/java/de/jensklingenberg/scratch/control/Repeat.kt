package de.jensklingenberg.scratch.control


import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createBlockRef
import de.jensklingenberg.scratch.common.createTimes
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

sealed interface RepeatOption {
    class Times(val times: Int) : RepeatOption
    class Until(val reporterBlock: ReporterBlock) : RepeatOption
}

fun Repeat(times: Int, vararg childs: Node) = Repeat(RepeatOption.Times(times), *childs)

fun NodeBuilder.repeat(times: Int, childs : NodeBuilder.()->Unit) = addChild(Repeat(times, *NodeBuilder().apply(childs).childs.toTypedArray()))

class Repeat(private val option: RepeatOption, private vararg val childs: Node) : BlockSpec(OpCode.control_repeat) {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        val name2 = identifier.toString()
        val newNext = nextUUID?.toString()
        val operatorUUID = UUID.randomUUID()

        val childUUIDS = childs.map { UUID.randomUUID() }
        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != childs.lastIndex

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                childUUIDS[childIndex],
                nextUUID,
                layer + 1,
                context
            )
        }

        val inputs = mutableMapOf(
            "TIMES" to when (option) {
                is RepeatOption.Times -> createTimes(option.times.toString())
                is RepeatOption.Until -> createBlockRef(operatorUUID.toString())
            }
        )


        if (childs.isNotEmpty()) {
            inputs["SUBSTACK"] = de.jensklingenberg.scratch.createSubStack(childUUIDS.first().toString())
        }

        visitors[name2] = BlockSpec(
            opcode = OpCode.control_repeat,
            inputs = inputs
        ).toBlock(newNext, parent, context.topLevel)

        if (option is RepeatOption.Until) {
            option.reporterBlock.visit(
                visitors,
                identifier.toString(),
                operatorUUID,
                null,
                layer + 1,
                context
            )
        }
    }
}