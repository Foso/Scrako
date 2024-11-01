package de.jensklingenberg.scratch3.sound

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch3.common.OpCode

private class ClearEffects : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sound_cleareffects,
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.clearEffects() = addNode(ClearEffects())