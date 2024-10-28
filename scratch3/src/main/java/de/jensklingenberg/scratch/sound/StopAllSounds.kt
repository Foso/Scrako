package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

class StopAllSounds : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

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