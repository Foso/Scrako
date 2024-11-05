package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class BackdropsImport : ImportNode {
    override val opCode: String = "looks_backdrops"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("backdrops()\n")
    }
}

class ItemNumOfListImport : ImportNode {
    override val opCode: String = "data_itemnumoflist"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        block: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("itemNumOfList(")
        handle(builder, target, myList, block.inputs["ITEM"]?.get(1))
        builder.append(", ")
        builder.append(block.fields["LIST"]?.get(0))
        builder.append(")\n")
    }
}