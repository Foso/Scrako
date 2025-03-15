package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ItemNumOfListImport : ImportNode {
    override val opCode: String = "data_itemnumoflist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFull: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("itemNumOfList(")
        handle(builder, target, myList, blockFull.inputs["ITEM"]?.get(1))
        builder.append(", ")
        builder.append(blockFull.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}