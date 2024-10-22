package me.jens.scratch.control


import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock
import me.jens.scratch.common.createBlockRef
import me.jens.scratch.common.createTimes
import scratch.createSubStack
import java.util.UUID

sealed interface RepeatOption{
    class Times(val times: Int): RepeatOption
    class Until(val reporterBlock: ReporterBlock): RepeatOption
}

fun Repeat(times: Int, vararg childs: Node) = Repeat(RepeatOption.Times(times), *childs)

class Repeat(private val option: RepeatOption, private vararg val childs: Node) : BlockSpec(OpCode.control_repeat) {
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
                layer+1,
                context
            )
        }

        val inputs = mutableMapOf("TIMES" to when(option){
            is RepeatOption.Times -> createTimes(option.times.toString())
            is RepeatOption.Until -> createBlockRef(operatorUUID.toString())
        })


        if (childs.isNotEmpty()) {
            inputs["SUBSTACK"] = createSubStack(childUUIDS.first().toString())
        }

        visitors[name2] = BlockSpec(
            opcode = OpCode.control_repeat,
            inputs = inputs
        ).toBlock(newNext, parent, layer == 0 && index == 0)

        if (option is RepeatOption.Until) {
            option.reporterBlock.visit(visitors, identifier.toString(), index + 1, operatorUUID, null, layer + 1, context)
        }
    }
}