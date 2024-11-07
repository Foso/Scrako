package de.jensklingenberg.scratch3.control

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private class Repeat(private val times: ReporterBlock, private vararg val childs: Node) :
    BlockSpec("control_repeat") {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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
            opcode = "control_repeat",
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

fun CommonScriptBuilder.repeat(times: Int, childs: CommonScriptBuilder.() -> Unit) = repeat(IntBlock(times), childs)
fun CommonScriptBuilder.repeat(times: Double, childs: CommonScriptBuilder.() -> Unit) = repeat(DoubleBlock(times), childs)

fun <T: CommonScriptBuilder> T.repeat(times: ReporterBlock, childs: CommonScriptBuilder.() -> Unit) =
    addNode(Repeat(times, *CommonScriptBuilder().apply(childs).childs.toTypedArray()))