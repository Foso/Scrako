package de.jensklingenberg.newimport.data

import de.jensklingenberg.example.newimport.handle
import de.jensklingenberg.newimport.ImportNode
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Target

class SetVariableImport : ImportNode {
    override val opCode: String = "data_setvariableto"

    override fun visit(
        builder: StringBuilder,
        target: Target,
        blockFullOr: BlockFull,
        myList: List<ImportNode>,
        blockId: String
    ) {
        builder.append("setVariableTo(")
        builder.append("${blockFullOr.fields["VARIABLE"]?.get(0)},")
        handle(builder, target, myList, blockFullOr.inputs["VALUE"]?.get(1))
        builder.append(")\n")
    }
}



