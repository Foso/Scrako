package de.jensklingenberg.newimport.sound

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SetVolumeToImport : ImportNode {
    override val opCode: String = "sound_setvolumeto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("setVolumeTo(")
        handle(builder, target, myList, blockOr.inputs["VOLUME"]?.get(1))
        builder.append(")\n")
    }
}

//sound_changevolumeby

class ChangeVolumeByImport : ImportNode {
    override val opCode: String = "sound_changevolumeby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changeVolumeBy(")
        handle(builder, target, myList, blockOr.inputs["VOLUME"]?.get(1))
        builder.append(")\n")
    }
}

