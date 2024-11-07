package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SubtractImport : ImportNode {
    override val opCode: String = "operator_subtract"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("(")
        handle(builder, target, myList, blockFullOr.inputs["NUM1"]?.get(1))
        builder.append(" minus ")
        handle(builder, target, myList, blockFullOr.inputs["NUM2"]?.get(1))
        builder.append(")")
    }
}
