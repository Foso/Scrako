package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ChangevariablebyImport : ImportNode {
    override val opCode: String = "data_changevariableby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        val variable = blockFullOr.fields["VARIABLE"]?.get(0)
        builder.append("changeVariableBy($variable,")
        handle(builder, target, myList, blockFullOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}
