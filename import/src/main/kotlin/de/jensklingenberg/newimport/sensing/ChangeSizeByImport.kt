package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class ChangeSizeByImport : ImportNode {
    override val opCode: String = "looks_changesizeby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("changeSizeBy(")
        builder.append(blockFullOr.inputs["CHANGE"]?.get(1))
        builder.append(")\n")
    }

}