package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class ClearEffects : Node {

    override fun visit(
        visitors: MutableMap<String, de.jensklingenberg.scratch.model.Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: de.jensklingenberg.scratch.common.Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_cleareffects,
        ).toBlock(nextUUID, parent)
    }
}

fun NodeBuilder.clearEffects() = addChild(ClearEffects())