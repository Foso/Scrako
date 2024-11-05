package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class ChangeXbyImport : ImportNode {
    override val opCode: String = "motion_changexby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changeXby(")
        handle(builder, target, myList, blockOr.inputs["DX"]?.get(1))
        builder.append(")\n")
    }
}

//sound_sounds_menu

//sound_playuntildone

