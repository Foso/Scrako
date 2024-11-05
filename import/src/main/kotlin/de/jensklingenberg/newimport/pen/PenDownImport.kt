package de.jensklingenberg.newimport.pen

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class PenDownImport : ImportNode {
    override val opCode: String = "pen_penDown"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("penDown()\n")
    }
}




