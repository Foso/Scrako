package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class RoundImport : ImportNode {
    override val opCode: String = "operator_round"
    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("round(")
        handle(builder, target, myList, blockFullOr.inputs["NUM"]?.get(1))
        builder.append(")")
    }

}