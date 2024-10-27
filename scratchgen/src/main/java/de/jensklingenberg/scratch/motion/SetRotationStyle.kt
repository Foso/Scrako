package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class SetRotationStyle(private val style: RotationStyle) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_setrotationstyle,
            fields = mapOf("STYLE" to listOf(style.spriteName, null))
        ).toBlock(nextUUID, parent)
    }
}

enum class RotationStyle(val spriteName: String) {
    ALL_AROUND("all around"),
    LEFT_RIGHT("left-right"),
    DONT_ROTATE("don't rotate")
}

fun ScriptBuilder.setRotationStyle(style: RotationStyle) = addChild(SetRotationStyle(style))