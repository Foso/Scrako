package de.jensklingenberg.newimport.pen

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class StampImport : ImportNode {
    override val opCode: String = "pen_stamp"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("stamp()\n")
    }
}





