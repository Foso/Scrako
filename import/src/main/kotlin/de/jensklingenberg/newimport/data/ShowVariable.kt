package de.jensklingenberg.newimport.data

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ShowVariable : ImportNode {
    override val opCode: String = "data_showvariable"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("showVariable(")
        builder.append(blockFullOr.fields["VARIABLE"]?.get(0))
        builder.append(")\n")
    }
}