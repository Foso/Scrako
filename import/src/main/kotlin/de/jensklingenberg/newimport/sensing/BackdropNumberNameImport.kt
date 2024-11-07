package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class BackdropNumberNameImport : ImportNode {
    override val opCode: String = "looks_backdropnumbername"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("backdrop")
        builder.append(blockFullOr.fields["NUMBER_NAME"]?.get(0)?.capitalize())
        builder.append("()\n")
    }

}