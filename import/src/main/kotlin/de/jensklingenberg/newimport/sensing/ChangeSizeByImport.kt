package de.jensklingenberg.newimport.sensing

import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.model.Target

class ChangeSizeByImport : ImportNode {
    override val opCode: String = "looks_changesizeby"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockOr: Block,
        myList: List<ImportNode>,
        id: String,
    ) {
        builder.append("changeSizeBy(")
        builder.append(blockOr.inputs["CHANGE"]?.get(1))
        builder.append(")\n")
    }

}