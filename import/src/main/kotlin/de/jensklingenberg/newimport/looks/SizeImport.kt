package de.jensklingenberg.newimport.looks

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class SizeImport : ImportNode {
    override val opCode: String = "looks_size"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("setSize(")
        handle(builder, target, myList, blockOr.inputs["SIZE"]?.get(1))
        builder.append(")\n")
    }
}