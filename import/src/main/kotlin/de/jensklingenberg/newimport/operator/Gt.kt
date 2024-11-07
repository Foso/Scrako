package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class GtImport : ImportNode {
    override val opCode: String = "operator_gt"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {

        builder.append("(")
        handle(builder, target, myList, blockOr.inputs["OPERAND1"]?.get(1))
        builder.append(" gt ")
        handle(builder, target, myList, blockOr.inputs["OPERAND2"]?.get(1))
        builder.append(")")
    }
}
