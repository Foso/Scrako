package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class Repeat(private val times: ReporterBlock, private vararg val childs: Node) :
    BlockSpec(OpCode.control_repeat) {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID().toString()

        val childUUIDS = childs.map { UUID.randomUUID().toString() }

        val inputs = mutableMapOf(
            "TIMES" to setValue(times, operatorUUID, context)
        )

        childs.firstOrNull()?.let {
            inputs["SUBSTACK"] = JsonArray(
                listOf(
                    JsonPrimitive(2),
                    JsonPrimitive(childUUIDS.first().toString())
                )
            )
        }

        visitors[identifier] = BlockSpec(
            opcode = OpCode.control_repeat,
            inputs = inputs
        ).toBlock(nextUUID, parent)

        times.visit(
            visitors,
            identifier,
            operatorUUID,
            null, context,)

        childs.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != childs.lastIndex

            val nextUUID = if (nextchild) childUUIDS[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier,
                childUUIDS[childIndex],
                nextUUID, context,

                )
        }


    }
}

fun ScriptBuilder.repeat(times: Int, childs: ScriptBuilder.() -> Unit) = repeat(IntBlock(times), childs)
fun ScriptBuilder.repeat(times: Double, childs: ScriptBuilder.() -> Unit) = repeat(DoubleBlock(times), childs)

fun ScriptBuilder.repeat(times: ReporterBlock, childs: ScriptBuilder.() -> Unit) =
    addNode(Repeat(times, *ScriptBuilder().apply(childs).childs.toTypedArray()))