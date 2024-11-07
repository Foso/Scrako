package de.jensklingenberg.newimport.sound

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ClearEffectsImport : ImportNode {
    override val opCode: String = "sound_cleareffects"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("clearEffects()\n")
    }
}