package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class AddToList : ImportNode {
    override val opCode: String = "data_addtolist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("addToList(")
        handle(builder, target, myList, blockOr.inputs["ITEM"]?.get(1))
        builder.append(",")
        builder.append(blockOr.fields["LIST"]?.get(0))


        builder.append(")\n")
    }
}