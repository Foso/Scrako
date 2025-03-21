package de.jensklingenberg.newimport.operator

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class MathOpImport : ImportNode {
    override val opCode: String = "operator_mathop"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("mathop(")
        builder.append(blockFullOr.fields["OPERATOR"]?.get(0)?.uppercase())
        builder.append(",")
        handle(builder, target, myList, blockFullOr.inputs["NUM"]?.get(1))

        builder.append(")")
    }
}