package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class MultiplyImport : ImportNode {
    override val opCode: String = "operator_multiply"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("(")
        handle(builder, target, myList, blockOr.inputs["NUM1"]?.get(1))
        builder.append(" times ")
        handle(builder, target, myList, blockOr.inputs["NUM2"]?.get(1))
        builder.append(")")
    }
}
