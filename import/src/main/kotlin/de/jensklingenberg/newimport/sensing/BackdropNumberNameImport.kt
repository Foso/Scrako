package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class BackdropNumberNameImport : ImportNode {
    override val opCode: String = "looks_backdropnumbername"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("backdrop")
        builder.append(blockOr.fields["NUMBER_NAME"]?.get(0)?.capitalize())
        builder.append("()\n")
    }

}