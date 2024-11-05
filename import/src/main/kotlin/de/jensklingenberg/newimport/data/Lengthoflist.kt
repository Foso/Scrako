package de.jensklingenberg.newimport.data

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class LengthoflistImport : ImportNode {
    override val opCode: String = "data_lengthoflist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("lengthoflist(")
        builder.append(blockOr.fields["LIST"]?.get(0))
        builder.append(")")
    }
}

