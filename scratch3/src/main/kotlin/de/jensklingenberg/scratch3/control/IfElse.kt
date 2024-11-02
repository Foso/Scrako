package de.jensklingenberg.scratch3.control


import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class IfElse(
    private val condition: BooleanBlock,
    private val leftStack: List<Node>,
    private val rightStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context) {
        val name2 = identifier
        val newNext = nextUUID
        val operatorUUID = UUID.randomUUID().toString()
        val leftUUIDs = leftStack.map { UUID.randomUUID().toString() }
        val rightUUIDs = rightStack.map { UUID.randomUUID().toString() }

        visitors[name2] = BlockSpec(
            opcode = "control_if_else",
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
                        JsonPrimitive(leftUUIDs.first().toString())
                    )
                ),
                "SUBSTACK2" to JsonArray(
                    listOf(
                        JsonPrimitive(2),
                        JsonPrimitive(rightUUIDs.first().toString())
                    )
                )
            )
        ).toBlock(newNext, parent)
        condition.visit(visitors, name2, operatorUUID, null, context)

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = name2,
                leftUUIDs[childIndex],
                nextUUID, context,

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
                nextUUID, context,

                )
        }

    }
}

fun CommonScriptBuilder.ifElse(
    operatorSpec: BooleanBlock,
    leftStack: CommonScriptBuilder.() -> Unit,
    rightStack: CommonScriptBuilder.() -> Unit,
) = addNode(
    IfElse(
        operatorSpec,
        leftStack = CommonScriptBuilder().apply(leftStack).childs,
        rightStack = CommonScriptBuilder().apply(rightStack).childs
    )
)
