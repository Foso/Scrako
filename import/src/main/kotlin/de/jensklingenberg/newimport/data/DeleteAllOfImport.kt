package de.jensklingenberg.newimport.data

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class DeleteAllOfImport : ImportNode {
    override val opCode: String = "data_deletealloflist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("deleteAllOf(")
        builder.append(blockFullOr.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}



