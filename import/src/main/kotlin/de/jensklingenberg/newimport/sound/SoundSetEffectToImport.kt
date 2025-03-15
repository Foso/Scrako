package de.jensklingenberg.newimport.sound

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SoundSetEffectToImport : ImportNode {
    override val opCode: String = "sound_seteffectto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("setEffectTo(")
        builder.append(blockFullOr.fields["EFFECT"]?.get(0))
        builder.append(", ")
        handle(builder, target, myList, blockFullOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}