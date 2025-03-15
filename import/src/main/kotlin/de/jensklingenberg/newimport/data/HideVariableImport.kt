package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class HideVariableImport : ImportNode {
    override val opCode: String = "data_hidevariable"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String,
    ) {
        builder.append("hideVariable(")
        handle(builder, target, myList, blockFullOr.inputs["VARIABLE"]?.get(1))
        builder.append(")\n")
    }
}


//looks_size

