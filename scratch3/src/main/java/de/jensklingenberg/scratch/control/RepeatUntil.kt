package de.jensklingenberg.scratch.control

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
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
        context: Context,

        ) {
        val operatorUUID = UUID.randomUUID()
        val leftUUIDs = leftStack.map { UUID.randomUUID() }

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_repeat_until,
            inputs = mapOf(
                "CONDITION" to JsonArray(
                    listOf(
                        JsonPrimitive(2),
                        JsonPrimitive(operatorUUID.toString())
                    )
                ),
                "SUBSTACK" to JsonArray(
                    listOf(
                        JsonPrimitive(2),
                        JsonPrimitive(leftUUIDs.firstOrNull().toString())
                    )
                )
            )
        ).toBlock(nextUUID, parent)
        condition.visit(visitors, identifier.toString(), operatorUUID, null, context)

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier.toString(),
                leftUUIDs[childIndex],
                nextUUID, context,

                )
        }

    }
}

fun ScriptBuilder.repeatUntil(
    block: BooleanBlock,
    leftStack: ScriptBuilder.() -> Unit
) = addNode(RepeatUntil(block, leftStack = ScriptBuilder().apply(leftStack).childs))