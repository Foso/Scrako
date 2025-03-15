package de.jensklingenberg.newimport.motion

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class MovestepsImport : ImportNode {
    override val opCode: String = "motion_movesteps"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("move(")
        handle(builder, target, myList, blockFullOr.inputs["STEPS"]?.get(1))
        builder.append(")\n")
    }
}
