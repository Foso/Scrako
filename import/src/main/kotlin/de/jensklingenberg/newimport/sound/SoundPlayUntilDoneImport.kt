package de.jensklingenberg.newimport.sound

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SoundPlayUntilDoneImport : ImportNode {
    override val opCode: String = "sound_playuntildone"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("playUntilDone(")
        handle(builder, target, myList, blockFullOr.inputs["SOUND_MENU"]?.get(1))
        builder.append(")\n")
    }
}

