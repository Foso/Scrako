package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class DivideImport : ImportNode {
    override val opCode: String = "operator_divide"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("(")
        handle(builder, target, myList, blockFullOr.inputs["NUM1"]?.get(1))
        builder.append(" div ")
        handle(builder, target, myList, blockFullOr.inputs["NUM2"]?.get(1))
        builder.append(")")
    }
}


//operator_contains

class ContainsImport : ImportNode {
    override val opCode: String = "operator_contains"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("(")
        handle(builder, target, myList, blockFullOr.inputs["STRING1"]?.get(1))
        builder.append(" in ")
        handle(builder, target, myList, blockFullOr.inputs["STRING2"]?.get(1))
        builder.append(")")
    }
}
