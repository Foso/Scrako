package de.jensklingenberg.scratch3.control

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.CBlock
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.builder.StageScriptBuilder
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

internal class IfThen(
    private val condition: BooleanBlock,
    private val leftStack: List<Node>
) : Node, CBlock {

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
            opcode = "control_if",
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


fun StageScriptBuilder.ifThen(
    block: BooleanBlock,
    leftStack: StageScriptBuilder.() -> Unit
) = addNode(IfThen(block, leftStack = StageScriptBuilder().apply(leftStack).getNodes()))

fun SpriteScriptBuilder.ifThen(
    block: BooleanBlock,
    leftStack: SpriteScriptBuilder.() -> Unit
) = addNode(IfThen(block, leftStack = SpriteScriptBuilder().apply(leftStack).getNodes()))

