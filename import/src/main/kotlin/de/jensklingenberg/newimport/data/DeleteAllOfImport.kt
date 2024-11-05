package de.jensklingenberg.newimport.data

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class DeleteAllOfImport : ImportNode {
    override val opCode: String = "data_deletealloflist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("deleteAllOf(")
        builder.append(blockOr.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}



