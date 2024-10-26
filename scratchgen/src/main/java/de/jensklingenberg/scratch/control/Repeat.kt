package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ScriptBuilder
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
        ).toBlock(nextUUID, parent)

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

fun ScriptBuilder.repeat(times: Int, childs: ScriptBuilder.() -> Unit) = repeat(IntBlock(times), childs)
fun ScriptBuilder.repeat(times: Double, childs: ScriptBuilder.() -> Unit) = repeat(DoubleBlock(times), childs)

fun ScriptBuilder.repeat(times: ReporterBlock, childs: ScriptBuilder.() -> Unit) =
    addChild(Repeat(times, *ScriptBuilder().apply(childs).childs.toTypedArray()))