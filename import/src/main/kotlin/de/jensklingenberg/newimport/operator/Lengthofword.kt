package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class LengthofWordImport : ImportNode {
    override val opCode: String = "operator_length"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("lengthof(")
        handle(builder, target, myList, blockFullOr.inputs["STRING"]?.get(1))
        builder.append(")")
    }
}
