package de.jensklingenberg.newimport.pen

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class PenUpImport : ImportNode {
    override val opCode: String = "pen_penUp"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("penUp()\n")
    }
}




