package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ChangeybyImport : ImportNode {
    override val opCode: String = "motion_changeyby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("changeYby(")
        handle(builder, target, myList, blockFullOr.inputs["DY"]?.get(1))
        builder.append(")\n")
    }
}
