package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull

private class SetRotationStyle(private val style: RotationStyle) : Node, MotionBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "motion_setrotationstyle",
            fields = mapOf("STYLE" to listOf(style.spriteName, null))
        ).toBlock(nextUUID, parent)
    }
}

enum class RotationStyle(val spriteName: String) {
    ALL_AROUND("all around"),
    LEFT_RIGHT("left-right"),
    DONT_ROTATE("don't rotate")
}

fun CommonScriptBuilder.setRotationStyle(style: RotationStyle) = addNode(SetRotationStyle(style))