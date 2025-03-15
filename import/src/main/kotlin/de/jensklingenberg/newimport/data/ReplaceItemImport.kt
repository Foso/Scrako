package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ReplaceItemImport : ImportNode {
    override val opCode: String = "data_replaceitemoflist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {

        builder.append("replaceItemOfListWith(")
        handle(builder, target, myList, blockFullOr.inputs["INDEX"]?.get(1))
        builder.append(", ")
        builder.append(blockFullOr.fields["LIST"]?.get(0))
        builder.append(", ")
        handle(builder, target, myList, blockFullOr.inputs["ITEM"]?.get(1))
        builder.append(")\n")

    }
}

