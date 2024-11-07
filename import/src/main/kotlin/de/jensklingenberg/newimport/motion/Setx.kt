package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SetxImport : ImportNode {
    override val opCode: String = "motion_setx"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("setX(")
        handle(builder, target, myList, blockFull.inputs["X"]?.get(1))
        builder.append(")\n")
    }
}
