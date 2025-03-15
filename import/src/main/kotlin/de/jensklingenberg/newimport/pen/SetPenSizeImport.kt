package de.jensklingenberg.newimport.pen

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SetPenSizeImport : ImportNode {
    override val opCode: String = "pen_setPenSizeTo"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("setPenSizeTo(")
        handle(builder, target, myList, blockFullOr.inputs["SIZE"]?.get(1))
        builder.append(")\n")
    }
}