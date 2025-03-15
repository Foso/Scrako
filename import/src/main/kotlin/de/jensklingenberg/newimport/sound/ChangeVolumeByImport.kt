package de.jensklingenberg.newimport.sound

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ChangeVolumeByImport : ImportNode {
    override val opCode: String = "sound_changevolumeby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changeVolumeBy(")
        handle(builder, target, myList, blockFullOr.inputs["VOLUME"]?.get(1))
        builder.append(")\n")
    }
}