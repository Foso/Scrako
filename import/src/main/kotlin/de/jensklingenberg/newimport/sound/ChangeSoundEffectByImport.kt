package de.jensklingenberg.newimport.sound

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.ScratchProject
import de.jensklingenberg.scrako.model.Target

class ChangeSoundEffectByImport : ImportNode {
    override val opCode: String = "sound_changeeffectby"

    override fun visit(
        builder: StringBuilder,
        project: ScratchProject,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changeEffectBy(")
        handle(builder, target, myList, project, blockOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}