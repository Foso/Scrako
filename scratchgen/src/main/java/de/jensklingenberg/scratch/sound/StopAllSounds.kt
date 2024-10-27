package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import java.util.UUID

class StopAllSounds : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
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