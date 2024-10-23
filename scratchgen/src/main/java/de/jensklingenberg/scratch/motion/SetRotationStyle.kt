package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class SetRotationStyle(private val style: RotationStyle) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_setrotationstyle,
            fields = mapOf("STYLE" to listOf(style.spriteName, null))
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
    }
}

enum class RotationStyle(val spriteName: String) {
    ALL_AROUND("all around"),
    LEFT_RIGHT("left-right"),
    DONT_ROTATE("don't rotate")
}

fun NodeBuilder.setRotationStyle(style: RotationStyle) = addChild(SetRotationStyle(style))