package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class LtImport : ImportNode {
    override val opCode: String = "operator_lt"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("(")
        handle(builder, target, myList, blockFullOr.inputs["OPERAND1"]?.get(1))
        builder.append(" lt ")
        handle(builder, target, myList, blockFullOr.inputs["OPERAND2"]?.get(1))
        builder.append(")")
    }
}


