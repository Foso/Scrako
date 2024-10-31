package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block

class StopAllSounds : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {

        visitors[identifier] = BlockSpec(
            opcode = "sound_stopallsounds",
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.stopAllSounds() = addNode(StopAllSounds())