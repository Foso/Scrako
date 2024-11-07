package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class RandomImport : ImportNode {
    override val opCode: String = "operator_random"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("random(")
        handle(builder, target, myList, blockOr.inputs["FROM"]?.get(1))
        builder.append(",")
        handle(builder, target, myList, blockOr.inputs["TO"]?.get(1))
        builder.append(")")
    }
}