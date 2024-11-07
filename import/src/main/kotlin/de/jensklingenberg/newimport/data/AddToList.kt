package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class AddToList : ImportNode {
    override val opCode: String = "data_addtolist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("addToList(")
        handle(builder, target, myList, blockFullOr.inputs["ITEM"]?.get(1))
        builder.append(",")
        builder.append(blockFullOr.fields["LIST"]?.get(0))


        builder.append(")\n")
    }
}

//data_listcontainsitem


class ListContainsImport : ImportNode {
    override val opCode: String = "data_listcontainsitem"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("listContainsItem(")
        handle(builder, target, myList, blockFullOr.inputs["ITEM"]?.get(1))
        builder.append(",")
        builder.append(blockFullOr.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}

