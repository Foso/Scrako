package de.jensklingenberg.scratch3.control

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.builder.StageScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class RepeatUntil(
    private val condition: BooleanBlock,
    private val leftStack: List<Node>
) : Node {

    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID().toString()
        val leftUUIDs = leftStack.map { UUID.randomUUID().toString() }

        visitors[identifier] = BlockSpec(
            opcode = "control_repeat_until",
            inputs = mapOf(
                "CONDITION" to setValue(condition, operatorUUID, context),
                "SUBSTACK" to JsonArray(
                    listOf(
                        JsonPrimitive(2),
                        JsonPrimitive(leftUUIDs.firstOrNull().toString())
                    )
                )
            )
        ).toBlock(nextUUID, parent)
        condition.visit(visitors, identifier, operatorUUID, null, context)

        leftStack.mapIndexed { childIndex, visitor ->
            val nextchild =
                childIndex != leftStack.lastIndex

            val nextUUID = if (nextchild) leftUUIDs[childIndex + 1] else null
            visitor.visit(
                visitors,
                parent = identifier,
                leftUUIDs[childIndex],
                nextUUID, context,
            )
        }
    }
}

fun StageScriptBuilder.repeatUntil(
    block: BooleanBlock,
    leftStack: StageScriptBuilder.() -> Unit
) = addNode(RepeatUntil(block, leftStack = StageScriptBuilder().apply(leftStack).getNodes()))

fun SpriteScriptBuilder.repeatUntil(
    block: BooleanBlock,
    leftStack: SpriteScriptBuilder.() -> Unit
) = addNode(RepeatUntil(block, leftStack = SpriteScriptBuilder().apply(leftStack).getNodes()))