package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

class StopAllSounds : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {

        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_stopallsounds,
            inputs = mapOf(

            ),
            fields = mapOf(

            )
        ).toBlock(nextUUID, parent)

    }
}

fun ScriptBuilder.stopAllSounds() = addChild(StopAllSounds())