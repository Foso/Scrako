package de.jensklingenberg.newimport.sound

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SetVolumeToImport : ImportNode {
    override val opCode: String = "sound_setvolumeto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("setVolumeTo(")
        handle(builder, target, myList, blockFullOr.inputs["VOLUME"]?.get(1))
        builder.append(")\n")
    }
}


//sound_volume

