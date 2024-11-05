package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class ItemoflistImport : ImportNode {
    override val opCode: String = "data_itemoflist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("itemOfXList(")
        handle(builder, target, myList, blockOr.inputs["INDEX"]?.get(1))
        builder.append(", ")
        builder.append(blockOr.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}
