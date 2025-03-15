package de.jensklingenberg.newimport.data

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class HidelistImport : ImportNode {
    override val opCode: String = "data_hidelist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("hideList(")
        builder.append(blockFullOr.fields["LIST"]?.get(0))
        builder.append(")")

    }
}
