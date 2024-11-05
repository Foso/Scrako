package de.jensklingenberg.newimport.sound

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SoundSoundsMenuImport : ImportNode {
    override val opCode: String = "sound_sounds_menu"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {

        builder.append(blockOr.fields["SOUND_MENU"]?.get(0))

    }
}